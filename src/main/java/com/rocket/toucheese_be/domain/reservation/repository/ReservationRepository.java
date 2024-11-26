package com.rocket.toucheese_be.domain.reservation.repository;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 스튜디오 예약 가능 시간 조회
    List<Reservation> findByStudioAndReservationDate(Studio studio, LocalDate date);

    // 회원을 예약 조회
    List<Reservation> findByMemberId(Long memberId);

    // 회원 ID 조회 + 상태
    List<Reservation> findByMemberIdAndStatus(Long memberId, ReservationStatus status);

}

