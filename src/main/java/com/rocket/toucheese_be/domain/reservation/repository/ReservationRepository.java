package com.rocket.toucheese_be.domain.reservation.repository;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 스튜디오 예약 가능 시간 조회
    List<Reservation> findByStudioAndReservationDate(Studio studio, LocalDate date);

    // 회원 ID와 상태 리스트로 예약 조회
    List<Reservation> findByMemberIdAndStatusInOrderByReservationDateAsc(Long memberId, List<ReservationStatus> statuses);

    // 완료된 예약 조회 및 정렬
    List<Reservation> findByMemberIdAndStatusInOrderByReservationDateDesc(Long memberId, List<ReservationStatus> statuses);

    // 특정 상태의 예약 조회
    Page<Reservation> findByStatus(ReservationStatus status, Pageable pageable);


    // 예약 중복 확인(cancel 상태는 무시)
    boolean existsByStudioAndReservationDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatusNot(
            Studio studio,
            LocalDate reservationDate,
            LocalTime startTime,
            LocalTime endTime,
            ReservationStatus status
    );
    // 예약 생성 날짜 정렬
    Page<Reservation> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 스케쥴러
    List<Reservation> findByStatusAndEndTimeBefore(ReservationStatus status, LocalTime endTime);

    List<Reservation> findAllByMemberId(Long memberId);

}

