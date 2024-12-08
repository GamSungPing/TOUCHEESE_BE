package com.rocket.toucheese_be.global.fcm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PushMsg {
    RESERVATION_SUCCEED("예약 확정", " 예약이 확정되었습니다."),
    RESERVATION_FAILED("예약 실패", " 예약이 실패하였습니다.");

    private final String title;
    private final String body;
}
