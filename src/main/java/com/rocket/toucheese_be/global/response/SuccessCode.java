package com.rocket.toucheese_be.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements ResponseCode {

    GET_STUDIO_LIST_SUCCESS(HttpStatus.OK, "스튜디오 전체 조회 성공"),
    GET_STUDIO_ONE_SUCCESS(HttpStatus.OK, "스튜디오 단일 조회 성공"),
    GET_CONCEPT_LIST_SUCCESS(HttpStatus.OK, "컨셉 전체 조회 성공"),
    GET_CONCEPT_ONE_SUCCESS(HttpStatus.OK, "컨셉 단일 조회 성공"),
    GET_STUDIO_LIST_BY_CONCEPT_SUCCESS(HttpStatus.OK, "컨셉 별 스튜디오 목록 조회 성공"),
    GET_STUDIO_RATING_SUCCESS(HttpStatus.OK, "컨셉, 인기순 정렬 조회 성공"),
    GET_STUDIO_LIST_BY_CONCEPT_AND_REGION_SUCCESS(HttpStatus.OK, "컨셉, 지역별 스튜디오 전체 조회 성공"),
    GET_STUDIO_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 가격순 정렬 조회 성공"),
    GET_STUDIO_REGION_RATING_SUCCESS(HttpStatus.OK, "컨셉, 지역, 인기순 정렬 조회 성공"),
    GET_STUDIO_REGION_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 지역, 가격순 정렬 조회 성공"),
    GET_STUDIO_RATING_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 인기순, 가격순 정렬 조회 성공"),
    GET_STUDIO_REGION_RATING_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 지역, 인기순, 가격순 정렬 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}
