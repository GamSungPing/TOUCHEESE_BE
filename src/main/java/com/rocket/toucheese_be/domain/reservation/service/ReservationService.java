package com.rocket.toucheese_be.domain.reservation.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.domain.member.service.DeviceService;
import com.rocket.toucheese_be.domain.reservation.dto.AvailableTimeListDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationListDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationReqDto;
import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.repository.ReservationRepository;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.repository.StudioRepository;
import com.rocket.toucheese_be.global.fcm.FcmService;
import com.rocket.toucheese_be.global.fcm.PushMsg;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StudioRepository studioRepository;
    private final MemberRepository memberRepository;
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

        // 스튜디오의 예약 가능한 시간 계산
        List<LocalTime> availableSlots = studio.getAvailableTimeSlots(date, reservations);

        // AvailableTimeListDto 반환
        return new AvailableTimeListDto(studio.getName(), availableSlots);
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

            // 이미 예약이 있는지 확인 (중복 예약 방지)
            boolean isOverlapping = reservationRepository.existsByStudioAndReservationDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                    studio,
                    reservationReqDto.reservationDate(),
                    reservationReqDto.reservationTime(),
                    reservationReqDto.reservationTime().plusMinutes(59).plusSeconds(59) // endTime 계산
            );

            if (isOverlapping) {
                throw new CustomException(ErrorCode.DUPLICATE_RESERVATION); // 예외 처리
            }

            // 예약 생성
            Reservation reservation = Reservation.create(member, studio, reservationReqDto);

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

    // 특정 상태의 예약 목록 조회
    public List<ReservationListDto> getReservationsByStatus(ReservationStatus status) {
        List<Reservation> reservations = reservationRepository.findByStatus(status);
        return reservations.stream()
                .map(ReservationListDto::from)
                .collect(Collectors.toList());
    }

    // 예약 상태를 confirm으로 변경
    public void confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
        reservation.confirm(); // 상태 변경 메서드 호출
    }

    // 예약 상태를 cancel로 변경
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
        reservation.cancel(); // 상태 변경 메서드 호출
    }

    // 완료된 예약 목록 조회
    public List<ReservationListDto> getCompletedReservationsByMember(Long memberId) {
        // 완료된 상태를 필터링
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatusOrderByReservationDateDesc(memberId, ReservationStatus.complete);

        if (reservations.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        // DTO로 변환
        return reservations.stream()
                .map(ReservationListDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    // 예약 취소
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
    public void sendPushMsg(Long memberId, Reservation reservation, PushMsg pushMsg) {

        // 해당 사용자 디바이스 찾기 - 디바이스 토큰 찾기 용도
        // 없으면 deviceService 단에서 예외 처리 됨
        Device device = deviceService.getDeviceByMemberId(memberId);

        // 푸시 알림 전송
        try {
            fcmService.sendPushMsg(
                    device.getDeviceToken(),
                    pushMsg,
                    reservation.getStudio().getName()
            );
        } catch (IOException | FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
  

    // 매일 자정에 실행하여 오늘날짜 이후로 지난 예약들은 상태 complete 변경
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void updateCompletedReservations() {
        LocalDate today = LocalDate.now();

        // 오늘 이전 예약 상태를 가져와 업데이트
        List<Reservation> reservations = reservationRepository.findByReservationDateBeforeAndStatus(today, ReservationStatus.confirm);

        reservations.forEach(Reservation::complete); // 상태 변경
        reservationRepository.saveAll(reservations); // 변경된 상태 저장
    }

}
