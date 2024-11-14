package com.rocket.toucheese_be.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    GET_STUDIO_LIST_SUCCESS(HttpStatus.OK, "스튜디오 전체 조회 성공"),
    GET_STUDIO_ONE_SUCCESS(HttpStatus.OK, "스튜디오 단일 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }

}
