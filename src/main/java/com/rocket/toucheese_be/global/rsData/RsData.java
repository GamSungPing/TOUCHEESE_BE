package com.rocket.toucheese_be.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Getter
public class RsData<T> {

    @NonNull
    String msg;

    @NonNull
    T data;

    public static <T> RsData<T> of(String msg) {
        return of(msg, null);
    }

    public static <T> RsData<T> of(String msg, T data) {
        return new RsData<>(msg, data);
    }
}
