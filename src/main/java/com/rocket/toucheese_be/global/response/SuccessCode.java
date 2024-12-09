package com.rocket.toucheese_be.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements ResponseCode {

    GET_STUDIO_LIST_SUCCESS(HttpStatus.OK, "스튜디오 전체 조회 성공"),
    GET_STUDIO_ONE_SUCCESS(HttpStatus.OK, "스튜디오 단일 조회 성공"),
    GET_STUDIO_DETAiL_SUCCESS(HttpStatus.OK, "스튜디오 상세 조회 성공"),
    GET_STUDIO_LIST_BY_CONCEPT_SUCCESS(HttpStatus.OK, "컨셉 별 스튜디오 목록 조회 성공"),
    GET_STUDIO_RATING_SUCCESS(HttpStatus.OK, "컨셉, 인기순 정렬 조회 성공"),
    GET_STUDIO_LIST_BY_CONCEPT_AND_REGION_SUCCESS(HttpStatus.OK, "컨셉, 지역별 스튜디오 전체 조회 성공"),
    GET_STUDIO_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 가격순 정렬 조회 성공"),
    GET_STUDIO_REGION_RATING_SUCCESS(HttpStatus.OK, "컨셉, 지역, 인기순 정렬 조회 성공"),
    GET_STUDIO_REGION_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 지역, 가격순 정렬 조회 성공"),
    GET_STUDIO_RATING_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 인기순, 가격순 정렬 조회 성공"),
    GET_STUDIO_REGION_RATING_PRICING_SUCCESS(HttpStatus.OK, "컨셉, 지역, 인기순, 가격순 정렬 조회 성공"),

    GET_CONCEPT_LIST_SUCCESS(HttpStatus.OK, "컨셉 전체 조회 성공"),
    GET_CONCEPT_ONE_SUCCESS(HttpStatus.OK, "컨셉 단일 조회 성공"),

    GET_PRODUCT_LIST_BY_STUDIO_SUCCESS(HttpStatus.OK, "특정 스튜디오 프로덕트 전체 조회 성공"),
    GET_PRODUCT_DETAIL_SUCCESS(HttpStatus.OK, "특정 프로덕트 상세 조회 성공"),

    // 예약
    GET_STUDIO_RESERVATION_ONE_SUCCESS(HttpStatus.OK, "해당 스튜디오의 예약 내용 조회 성공"),
    GET_STUDIO_RESERVATION_AVAILABLE_LIST_SUCCESS(HttpStatus.OK, "해당 스튜디오의 예약 가능 시간을 조회 성공"),
    GET_MEMBER_RESERVATIONS_SUCCESS(HttpStatus.OK, "해당 멤버가 한 예약 조회 성공"),
    CANCEL_RESERVATION_SUCCESS(HttpStatus.OK, "스튜디오 예약 취소 성공"),
    CREATE_RESERVATION_SUCCESS(HttpStatus.OK, "스튜디오 예약 생성 성공"),
    GET_MEMBER_COMPLETED_RESERVATIONS_SUCCESS(HttpStatus.OK, "과거 예약 목록 조회 성공"),


    // 리뷰
    GET_STUDIO_TOTAL_REVIEW_PHOTO_LIST(HttpStatus.OK, "스튜디오 리뷰 사진 전체 조회 성공"),
    GET_REVIEW_DETAIL(HttpStatus.OK, "스튜디오 리뷰 상세 조회 성공"),
    GET_PRODUCT_REVIEW_LIST(HttpStatus.OK, "스튜디오 상품 리뷰 목록 조회"),


    // FCM 관련
    GET_DEVICE_TOKEN_SUCCESS(HttpStatus.OK, "디바이스 토큰 저장 성공");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}