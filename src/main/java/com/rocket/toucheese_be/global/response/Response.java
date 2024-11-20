package com.rocket.toucheese_be.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Getter
public class Response<T> {

    @NonNull
    int statusCode;

    @NonNull
    String msg;

    T data;

    // return data 있음
    public static <T> Response<T> of(ResponseCode responseCode, T data) {
        return of(responseCode.getHttpStatusCode(), responseCode.getMessage(), data);
    }

    // return data 없음
    public static <T> Response<T> of(ResponseCode responseCode) {
        return of(responseCode.getHttpStatusCode(), responseCode.getMessage(), null);
    }

    public static <T> Response<T> of(int statusCode, String msg, T data) {
        return new Response<>(statusCode, msg, data);
    }
}
