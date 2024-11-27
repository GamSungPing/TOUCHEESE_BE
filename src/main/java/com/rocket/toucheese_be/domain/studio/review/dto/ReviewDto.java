package com.rocket.toucheese_be.domain.studio.review.dto;

import com.rocket.toucheese_be.domain.studio.review.entity.Photo;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;

import java.util.List;

public record ReviewDto (
        Long id,
        List<String> imageString
) {
    public static ReviewDto from(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getPhotos() == null ? List.of() :
                        review.getPhotos().stream()
                                .map(Photo::getPhotoUrl)
                                .toList()
        );
    }
}
