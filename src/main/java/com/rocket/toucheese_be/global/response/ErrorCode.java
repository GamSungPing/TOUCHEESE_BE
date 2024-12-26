package com.rocket.toucheese_be.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ResponseCode {

    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 리소스입니다."),

    // 스튜디오
    NOT_FOUND_STUDIO(HttpStatus.NOT_FOUND, "해당 스튜디오가 존재하지 않습니다."),
    NOT_FOUND_CONCEPT_STUDIO(HttpStatus.NOT_FOUND, "해당 컨셉에 해당하는 스튜디오가 존재하지 않습니다."),

    // 리뷰
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당 스튜디오에 리뷰가 존재하지 않습니다."),
    NOT_FOUND_PRODUCT_REVIEW(HttpStatus.NOT_FOUND, "해당 상품 리뷰가 존재하지 않습니다."),


    // 상품
    NOT_FOUND_STUDIO_PRODUCT(HttpStatus.NOT_FOUND, "스튜디오에 해당하는 상품이 존재하지 않습니다."),
    NOT_FOUND_STUDIO_PRODUCT_DETAIL(HttpStatus.NOT_FOUND, "해당 상품이 존재하지 않습니다."),


    // 예약
    NOT_FOUND_RESERVATION(HttpStatus.NOT_FOUND, "예약이 존재하지 않습니다."),
    NOT_FOUND_MEMBER_RESERVATION(HttpStatus.NOT_FOUND, "해당 멤버의 예약이 아닙니다."),
    DUPLICATE_RESERVATION(HttpStatus.NOT_FOUND, "해당 시간은 예약이 불가합니다."),

    // 회원
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당 회원이 존재하지 않습니다."),
    NOT_FOUND_DEVICE(HttpStatus.NOT_FOUND, "해당 회원의 디바이스가 존재하지 않습니다."),
    NOT_FOUND_DEVICE_TOKEN_IN_REDIS(HttpStatus.NOT_FOUND, "Redis에 해당 회원의 디바이스 토큰이 존재하지 않습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰"),
    // 유효하지 않은 엑세스 토큰은 CustomJwtAuthenticationEntryPoint에서 따로 관리
    NOT_LOGIN_YET(HttpStatus.UNAUTHORIZED, "로그인 상태가 아닙니다."); // 400 하려다 jwt 토큰 관련은 통일

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}
