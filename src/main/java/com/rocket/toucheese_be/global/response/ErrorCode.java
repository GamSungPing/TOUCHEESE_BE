package com.rocket.toucheese_be.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ResponseCode {

    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 리소스입니다."),
    INVALID_VALUE_EXCEPTION(HttpStatus.BAD_REQUEST,  "올바르지 않은 요청 값입니다."),
    UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED,  "권한이 없는 요청입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}
