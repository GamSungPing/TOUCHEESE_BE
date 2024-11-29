package com.rocket.toucheese_be.domain.studio.studio.dto;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewPhotoDto;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import org.springframework.data.domain.Page;

import java.util.List;

public record StudioDetailDto(
        Long studioId,
        String name,
        String detailImageStrings,
        Double rating,
        int reviewCount,
        String businessHours,
        String address,
        String notice,
        List<ProductDto> products,
        Page<ReviewPhotoDto> reviews
) {
    public StudioDetailDto(Studio studio, List<ProductDto> products, Page<ReviewPhotoDto> reviews) {
        this(
                studio.getId(),
                studio.getName(),
                studio.getProfileImage().getProfileURL(),
                studio.getRating(),
                studio.getReviewList().size(),
                studio.getOpeningTime() + "-" + studio.getClosingTime()+" / 매주 "+getThisHolidays(studio)+" 휴무",
                studio.getAddress(),
                studio.getNotice(),
                products,
                reviews
        );
    }

    private static String getThisHolidays(Studio studio) {
        String[] holidays = studio.getHolidays().split("&");
        return String.join(", ", holidays);
    }

}