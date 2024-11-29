package com.rocket.toucheese_be.domain.studio.review.dto;

import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;


public record ReviewDto(
        Long reviewId,    // 리뷰 ID
        String imageUrl   // 리뷰 안에 이미지 URL
) {
    public static ReviewDto fromReviewPhoto(Review review, ReviewPhoto photo) {
        return new ReviewDto(review.getId(), photo.getPhotoUrl());
    }
}
