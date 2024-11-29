package com.rocket.toucheese_be.domain.studio.review.dto;

import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewDetailDto(
        String userProfileImageString,
        String userName,
        LocalDateTime dateString, // 리뷰가 작성된 날짜
        List<String> imageStrings, // 리뷰 이미지
        String content,
        Double rating,
        ReplyDto reply
) {
    public static ReviewDetailDto from(Review review) {
        return new ReviewDetailDto(
                review.getMember().getProfileImageUrl(),
                review.getMember().getName(),
                review.getCreatedAt(),
                review.getReviewPhotos() == null ? List.of() :
                        review.getReviewPhotos().stream()
                                .map(ReviewPhoto::getPhotoUrl)
                                .toList(),
                review.getContent(),
                review.getRating(),
                review.getReply() == null ? null : ReplyDto.from(review.getReply()) // Reply가 null인지 확인
        );
    }
}
