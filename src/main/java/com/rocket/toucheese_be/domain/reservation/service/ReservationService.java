package com.rocket.toucheese_be.domain.reservation.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.domain.member.service.DeviceService;
import com.rocket.toucheese_be.domain.reservation.dto.*;
import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.repository.ReservationRepository;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import com.rocket.toucheese_be.domain.studio.product.repository.ProductRepository;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.repository.StudioRepository;
import com.rocket.toucheese_be.global.fcm.FcmService;
import com.rocket.toucheese_be.global.fcm.PushMsg;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StudioRepository studioRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final Object lock = new Object(); // 동기화를 위한 락 객체
    private final FcmService fcmService;
    private final DeviceService deviceService;

    // 스튜디오 예약 단일 조회
    public ReservationDto getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        return ReservationDto.from(reservation);
    }

    // 예약 가능 시간 조회
    public AvailableTimeListDto getAvailableTimeSlots(Long studioId, LocalDate date) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STUDIO));

        // 특정 날짜의 모든 예약 조회
        List<Reservation> reservations = reservationRepository.findByStudioAndReservationDate(studio, date);

        // 상태가 cancel이 아닌 예약만 필터링
        List<Reservation> activeReservations = reservations.stream()
                .filter(reservation -> reservation.getStatus() != ReservationStatus.cancel)
                .toList();

        // 스튜디오의 예약 가능한 시간 계산
        List<LocalTime> availableSlots = studio.getAvailableTimeSlots(date, activeReservations);

        LocalTime lastSlot = availableSlots.isEmpty() ? null : availableSlots.get(availableSlots.size() - 1);

        // AvailableTimeListDto 반환
        return new AvailableTimeListDto(studio.getName(), availableSlots, studio.getOpeningTime(), lastSlot);
    }

    @Transactional
    public ReservationDto createReservation(ReservationReqDto reservationReqDto) {
        synchronized (lock) {
            // 회원 조회
            Member member = memberRepository.findById(reservationReqDto.memberId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

            // 스튜디오 조회
            Studio studio = studioRepository.findById(reservationReqDto.studioId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STUDIO));

            // 상품 조회
            Product product = productRepository.findById(reservationReqDto.productId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STUDIO_PRODUCT));


            // 이미 예약이 있는지 확인 (중복 예약 방지)
            boolean isOverlapping = reservationRepository.existsByStudioAndReservationDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatusNot(
                    studio,
                    reservationReqDto.reservationDate(),
                    reservationReqDto.reservationTime(),
                    reservationReqDto.reservationTime().plusMinutes(59).plusSeconds(59), // endTime 계산
                    ReservationStatus.cancel // cancel 상태는 제외
            );

            if (isOverlapping) {
                throw new CustomException(ErrorCode.DUPLICATE_RESERVATION); // 예외 처리
            }

            // 예약 생성
            Reservation reservation = Reservation.create(member, studio, product, reservationReqDto);

            // 예약 저장
            Reservation savedReservation = reservationRepository.save(reservation);

            // DTO로 변환 후 반환
            return ReservationDto.from(savedReservation);
        }
    }


    // 특정 멤버가 예약한 모든 예약 조회
    public List<ReservationListDto> getReservationsByMember(Long memberId) {
        // 확인 및 대기 상태를 조건으로 추가
        List<ReservationStatus> statuses = List.of(ReservationStatus.confirm, ReservationStatus.waiting);

        // 멤버 ID와 상태 조건으로 예약 목록 조회
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatusInOrderByReservationDateAsc(memberId, statuses);

        // 조회된 예약이 없더라도 빈 리스트 반환
        return reservations.stream()
                .map(ReservationListDto::from)
                .collect(Collectors.toList());
    }


    @Transactional
    // 예약 상태를 confirm으로 변경 (관리자 전용)
    public void confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
        reservation.confirm(); // 상태 변경 메서드 호출
        Long memberId = reservation.getMember().getId();
        sendPushMsg(memberId, reservation, PushMsg.RESERVATION_SUCCEED);
    }

    @Transactional
    // 예약 상태를 cancel로 변경 (관리자 전용)
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
        reservation.cancel(); // 상태 변경 메서드 호출
        Long memberId = reservation.getMember().getId();
        sendPushMsg(memberId, reservation, PushMsg.RESERVATION_FAILED);
    }

    // 완료 및 취소 상태 예약 목록 조회
    public List<ReservationListDto> getCompletedAndCancelledReservationsByMember(Long memberId) {
        // 완료 또는 취소 상태를 필터링
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatusInOrderByReservationDateDesc(
                memberId, Arrays.asList(ReservationStatus.complete, ReservationStatus.cancel));

        // DTO로 변환
        return reservations.stream()
                .map(ReservationListDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    // 예약 취소 (사용자가)
    public void cancelReservation(Long reservationId, Long memberId) {
        // 해당 예약 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        // 예약이 해당 멤버의 예약인지 확인
        if (!reservation.getMember().getId().equals(memberId)) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER_RESERVATION);
        }

        // 예약 상태를 취소로 변경
        reservation.cancel();

    }


    /*
    * 알림 로직
    * memberId - 푸시 메시지 받아야 하는 멤버 아이디
    * reservation - studio 이름을 얻기 위한 것이므로 Studio, studioName 등으로 수정 가능
    * pushMsg - 메시지 종류 PushMsg ENUM 참고 -> 예약 성공: PushMsg.RESERVATION_SUCCEED, 예약 실패: PushMsg.RESERVATION_FAILED
     */
    // 푸시 알림을 보내는 메서드
    public void sendPushMsg(Long memberId, Reservation reservation, PushMsg pushMsg) {
        // 해당 사용자 디바이스 토큰을 Redis에서 가져옵니다
        String deviceToken = deviceService.getDeviceTokenFromRedis(memberId); // Redis에서 토큰 확인

        // 만약 토큰이 존재하면 푸시 메시지를 전송합니다
        if (deviceToken != null) {
            try {
                fcmService.sendPushMsg(
                        deviceToken,
                        pushMsg,
                        reservation.getStudio().getName()
                );
            } catch (IOException | FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 토큰이 없으면 알림을 보내지 않음
            // 필요한 경우 로깅 또는 다른 처리를 할 수 있습니다.
            log.info("Device token not found, push notification not sent.");
        }
    }


    // 예약 대기 상태 목록 조회 (관리자 전용)
    public Page<ReservationAdminList> getReservationsByStatus(ReservationStatus status, Pageable pageable) {
        Page<Reservation> reservations = reservationRepository.findByStatus(status, pageable);
        return reservations.map(ReservationAdminList::from);
    }


    // 예약 전체 조회 (관리자 전용)
    public Page<ReservationAdminList> getAllReservationsSortedByCreatedAt(Pageable pageable) {
        // 모든 예약을 createdAt 기준으로 내림차순 정렬하여 페이지 단위로 조회
        Page<Reservation> reservations = reservationRepository.findAllByOrderByCreatedAtDesc(pageable);
        // DTO로 변환 후 반환
        return reservations.map(ReservationAdminList::from);
    }

    @Transactional
    public void deleteByMember(Long memberId) {
        List<Reservation> reservationList = reservationRepository.findAllByMemberId(memberId);
        reservationRepository.deleteAll(reservationList);
    }

}
