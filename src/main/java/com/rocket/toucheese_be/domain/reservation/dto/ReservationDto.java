package com.rocket.toucheese_be.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.reservation.entity.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto(
        @JsonIgnore Long id,
        String studioName, // 스튜디오 이름
        String memberName, // 예약자 성함
        String memberEmail, // 예약자 이메일
        LocalDate reservationDate, // 촬영 예약 날짜
        LocalTime startTime, // 촬영 시작 시잔
        String productOption, // 상품 옵션
        Integer totalPrice, // 총 가격
        String studioAddress

) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getStudio().getName(),
                reservation.getMember().getName(),
                reservation.getMember().getEmail(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getProductOption(),
                reservation.getTotalPrice(),
                reservation.getStudio().getAddress()
        );
    }
}
