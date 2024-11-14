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
    String msg;

    @NonNull
    T data;

    public static <T> Response<T> of(String msg) {
        return of(msg, null);
    }

    public static <T> Response<T> of(String msg, T data) {
        return new Response<>(msg, data);
    }
}
