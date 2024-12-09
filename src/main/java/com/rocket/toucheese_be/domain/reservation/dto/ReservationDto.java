package com.rocket.toucheese_be.domain.reservation.dto;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto(
        Long id,
        String studioImg,
        Long studentId,
        String studioName, // 스튜디오 이름
        String phoneNumber, // 예약자 핸드폰 번호
        String memberName, // 예약자 성함
        String memberEmail, // 예약자 이메일
        LocalDate reservationDate, // 촬영 예약 날짜
        LocalTime reservationTime, // 촬영 시작 시잔
        String productName, // 상품 이름
        String productOption, // 상품 옵션
        Integer totalPrice, // 총 가격
        String studioAddress, // 스튜디오 주소
        ReservationStatus reservationStatus

) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getStudio().getProfileImage().getProfileURL(),
                reservation.getStudio().getId(),
                reservation.getStudio().getName(),
                reservation.getPhoneNumber(),
                reservation.getMember().getName(),
                reservation.getEmail(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getProductName(),
                reservation.getProductOption(),
                reservation.getTotalPrice(),
                reservation.getStudio().getAddress(),
                reservation.getStatus()
        );
    }
}
