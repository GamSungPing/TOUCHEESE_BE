package com.rocket.toucheese_be.domain.reservation.dto;

import java.time.LocalTime;
import java.util.List;

public record AvailableTimeListDto(
        String studioName,              // 스튜디오 이름
        List<LocalTime> availableSlots, // 예약 가능한 시간 리스트
        LocalTime openingTime,
        LocalTime lastReservationTime
) {

}
