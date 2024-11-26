package com.rocket.toucheese_be.domain.reservation.service;

import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.domain.reservation.dto.AvailableTimeListDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationDto;
import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.repository.ReservationRepository;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StudioRepository studioRepository;
    private final MemberRepository memberRepository;


    // 스튜디오 예약 단일 조회
    public ReservationDto getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약이 존재하지 않습니다."));

        return ReservationDto.from(reservation);
    }

    // 예약 가능 시간 조회
    public AvailableTimeListDto getAvailableTimeSlots(Long studioId, LocalDate date) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new IllegalArgumentException("스튜디오를 찾을 수 없습니다."));

        // 특정 날짜의 모든 예약 조회
        List<Reservation> reservations = reservationRepository.findByStudioAndReservationDate(studio, date);

        // 스튜디오의 예약 가능한 시간 계산
        List<LocalTime> availableSlots = studio.getAvailableTimeSlots(date, reservations);

        // AvailableTimeListDto 반환
        return new AvailableTimeListDto(studio.getName(), availableSlots);
    }

    // 특정 멤버가 예약한 모든 예약 조회
//    public List<ReservationDto> getReservationsByMember(Long memberId) {
//        // 멤버를 찾고 해당 멤버가 예약한 모든 예약을 조회
//        List<Reservation> reservations = reservationRepository.findByMemberId(memberId);
//
//        // 예약 목록을 DTO로 변환하여 반환
//        return reservations.stream()
//                .map(ReservationDto::from)
//                .collect(Collectors.toList());
//    }
    public List<ReservationDto> getReservationsByMember(Long memberId) {
        // 멤버 ID와 CONFIRMED 상태로 예약 조회
        List<Reservation> reservations = reservationRepository.findByMemberIdAndStatus(memberId, ReservationStatus.예약확정);

        // 예약 목록을 DTO로 변환하여 반환
        return reservations.stream()
                .map(ReservationDto::from)
                .collect(Collectors.toList());
    }

    // 예약 취소
    public void cancelReservation(Long reservationId, Long memberId) {
        // 해당 예약 조회
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약이 존재하지 않습니다."));

        // 예약이 해당 멤버의 예약인지 확인
        if (!reservation.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("해당 멤버의 예약이 아닙니다.");
        }

        // 예약 상태를 취소로 변경
        reservation.cancel();

    }

}
