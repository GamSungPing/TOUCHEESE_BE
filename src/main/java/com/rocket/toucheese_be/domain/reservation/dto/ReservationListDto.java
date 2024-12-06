package com.rocket.toucheese_be.domain.reservation.dto;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationListDto (
        Long id,
        String studioImage,
        String studioName,
        LocalDate reservationDate,
        LocalTime reservationTime,
        ReservationStatus reservationStatus
) {

    public static ReservationListDto from (Reservation reservation) {
        return new ReservationListDto(
                reservation.getId(),
                reservation.getStudio().getProfileImage().getProfileURL(),
                reservation.getStudio().getName(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getStatus()

        );
    }
}
