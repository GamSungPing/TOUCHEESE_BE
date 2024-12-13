package com.rocket.toucheese_be.standard;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.repository.ReservationRepository;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationStatusScheduler {

    private final ReservationRepository reservationRepository;

    // 12시간마다 실행: 'waiting' 상태인 예약 취소
    @Scheduled(cron = "0 0 */12 * * *")
    @Transactional
    public void cancelPastReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            // 예약 상태가 'waiting'이고, 예약 시간이 현재를 넘었으면 취소 처리
            if (reservation.getStatus() == ReservationStatus.waiting && isPastReservation(reservation)) {
                reservation.cancel();
                reservationRepository.save(reservation);  // 상태 변경 후 저장
            }
        }
    }

    // 1시간마다 실행: 'confirm' 상태인 예약을 'complete'로 변경
    @Scheduled(cron = "0 0 */1 * * *")
    @Transactional
    public void completePastReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation : reservations) {
            // 예약 상태가 'confirm'이고, 예약 시간이 현재를 넘었으면 완료 처리
            if (reservation.getStatus() == ReservationStatus.confirm && isPastReservation(reservation)) {
                reservation.complete();
                reservationRepository.save(reservation);  // 상태 변경 후 저장
            }
        }
    }

    // 예약이 현재 시간을 넘었는지 확인하는 메서드
    private boolean isPastReservation(Reservation reservation) {
        LocalDateTime reservationDateTime = LocalDateTime.of(reservation.getReservationDate(), reservation.getStartTime());
        return reservationDateTime.isBefore(LocalDateTime.now());
    }
}



