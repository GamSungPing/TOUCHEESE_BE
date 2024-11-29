package com.rocket.toucheese_be.domain.studio.studio.dto;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.standard.PageDto;

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
        PageDto<ReviewDto> reviews
) {
    public StudioDetailDto(Studio studio, List<ProductDto> products, PageDto<ReviewDto> reviews) {
        this(
                studio.getId(),
                studio.getName(),
                studio.getProfileImage().getProfileURL(),
                studio.getRating(),
                studio.getReviewList().size(),
                studio.getOpeningTime() + "-" + studio.getClosingTime() + getThisHolidays(studio),
                studio.getAddress(),
                studio.getNotice(),
                products,
                reviews
        );
    }

    private static String getThisHolidays(Studio studio) {
        String holidays = studio.getHolidays();
        if(holidays == null) return "";
        return " / 매주 "+String.join(", ", holidays.split("&"))+" 휴무";
    }

}