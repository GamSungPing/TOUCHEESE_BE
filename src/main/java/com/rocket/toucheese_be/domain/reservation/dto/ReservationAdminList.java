package com.rocket.toucheese_be.domain.reservation.dto;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationAdminList (
        Long id,
        String studioImage,
        String studioName,
        LocalDate reservationDate,
        LocalTime reservationTime,
        ReservationStatus reservationStatus,
        String phoneNumber, // 예약자 전화번호
        String email     // 예약자 이메일
) {

    public static ReservationAdminList from(Reservation reservation) {
        return new ReservationAdminList(
                reservation.getId(),
                reservation.getStudio().getProfileImage().getProfileURL(),
                reservation.getStudio().getName(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getStatus(),
                reservation.getPhoneNumber(),
                reservation.getEmail()
        );
    }
}
