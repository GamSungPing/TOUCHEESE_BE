package com.rocket.toucheese_be.global.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    GET_STUDIO_LIST_EXCEPTION(HttpStatus.OK, "스튜디오 전체 조회 오류 발생"),
    GET_STUDIO_ONE_EXCEPTION(HttpStatus.OK, "스튜디오 단일 조회 오류 발생");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
