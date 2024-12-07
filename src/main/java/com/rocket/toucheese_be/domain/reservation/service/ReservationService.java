package com.rocket.toucheese_be.domain.reservation.service;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.domain.reservation.dto.AvailableTimeListDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationListDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationReqDto;
import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.repository.ReservationRepository;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.repository.StudioRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if (reservations.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        // 예약 목록을 DTO로 변환하여 반환
        return reservations.stream()
                .map(ReservationListDto::from)
                .collect(Collectors.toList());
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
