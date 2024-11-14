package com.rocket.toucheese_be.global.response;

public interface ResponseCode {
    int getHttpStatusCode();
    String getMessage();
}