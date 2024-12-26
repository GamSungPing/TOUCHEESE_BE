package com.rocket.toucheese_be.domain.studio.review.dto;

public record ReviewDto(
        Long reviewId,    // 리뷰 ID
        String imageUrl   // 리뷰 안에 이미지 URL
) {
}
