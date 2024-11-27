package com.rocket.toucheese_be.domain.studio.review.dto;

import com.rocket.toucheese_be.domain.studio.review.entity.Photo;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewDetailDto (
        String userProfileImageString,
        String userName,
        LocalDateTime dateString, // 리부가 작성된 날짜
        List<String> imageString, // 리뷰 이미지
        String content,
        Double rating,
        ReplyDto reply
) {

    public static ReviewDetailDto from(Review review) {
        return new ReviewDetailDto(
                review.getMember().getProfileImageUrl(),
                review.getMember().getName(),
                review.getCreatedAt(),
                review.getPhotos() == null ? List.of() :
                        review.getPhotos().stream()
                                .map(Photo::getPhotoUrl)
                                .toList(),
                review.getContent(),
                review.getStudio().getRating(),
                ReplyDto.from(review.getReply())
        );
    }
}

/*
*
struct ReviewDetail {
    let userProfileImageString: String
    let userName: String
    let dateString: String

    let imageStrings: [String]
    let content: String
    let rating: Double

    let reply: Reply?
}
* */