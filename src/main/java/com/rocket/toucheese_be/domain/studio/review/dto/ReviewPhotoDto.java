package com.rocket.toucheese_be.domain.studio.review.dto;

import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;


public record ReviewPhotoDto(
        Long id, // 리뷰 ID (사진 클릭 시 리뷰 상세 화면으로 이동)
        String imageString // 사진 URL
) {
    public static ReviewPhotoDto from(ReviewPhoto reviewPhoto) {
        return new ReviewPhotoDto(
                reviewPhoto.getReview().getId(),
                reviewPhoto.getPhotoUrl()
        );
    }
}
