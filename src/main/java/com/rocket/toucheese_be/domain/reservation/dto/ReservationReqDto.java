package com.rocket.toucheese_be.domain.reservation.dto;

import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationReqDto(
        @NotNull(message = "회원 ID는 필수입니다.") Long memberId,
        @NotNull(message = "스튜디오 ID는 필수입니다.") Long studioId,
        @NotNull(message = "예약 날짜는 필수입니다.") LocalDate reservationDate,
        @NotNull(message = "예약 시작 시간은 필수입니다.") LocalTime startTime,
        @NotNull(message = "프로덕트 ID는 필수입니다.") Long productId,
        String productOption,
        @NotNull(message = "총 가격입니다.") int totalPrice
) {
}
