package com.rocket.toucheese_be.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto(
        @JsonIgnore Long id,
        String memberName,
        String studioName,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        ReservationStatus status
) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getMember().getName(),
                reservation.getStudio().getName(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStatus()
        );
    }
}
