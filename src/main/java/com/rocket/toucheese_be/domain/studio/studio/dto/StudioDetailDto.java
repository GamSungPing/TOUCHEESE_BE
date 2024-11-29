package com.rocket.toucheese_be.domain.studio.studio.dto;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import org.springframework.data.domain.Page;

import java.util.List;

public record StudioDetailDto(
        Long studioId,
        String name,
        String detailImageStrings,
        Double rating,
        int reviewCount,
        String businessHours, // TODO: 앱 단이랑 합의 필요
        String address,
        String notice,
        List<ProductDto> products,
        Page<ReviewDto> reviews
) {
    public StudioDetailDto(Studio studio, List<ProductDto> products, Page<ReviewDto> reviews) {
        this(
                studio.getId(),
                studio.getName(),
                studio.getProfileImage().getProfileURL(),
                studio.getRating(),
                studio.getReviewList().size(),
                studio.getOpeningTime() + "~" + studio.getClosingTime(),
                studio.getAddress(),
                studio.getNotice(),
                products,
                reviews // TODO: 주윤님 코드랑 연결
        );
    }

}