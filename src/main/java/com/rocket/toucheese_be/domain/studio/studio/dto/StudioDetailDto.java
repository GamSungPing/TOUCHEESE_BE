package com.rocket.toucheese_be.domain.studio.studio.dto;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.studio.entity.Portfolio;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.standard.PageDto;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public record StudioDetailDto(
        Long studioId,
        String name,
        String[] detailImageStrings,
        Double rating,
        int reviewCount,
        LocalTime openTime,
        LocalTime closeTime,
        int[] holidays,
        String address,
        String notice,
        List<ProductDto> products,
        PageDto<ReviewDto> reviews
) {
    public StudioDetailDto(Studio studio, List<ProductDto> products, PageDto<ReviewDto> reviews) {
        this(
                studio.getId(),
                studio.getName(),
                getDetailImgs(studio.getPortfolios()),
                studio.getRating(),
                studio.getReviewList().size(),
                studio.getOpeningTime(),
                studio.getClosingTime(),
                getThisHolidays(studio),
                studio.getAddress(),
                studio.getNotice().replace("\\n", "\n"),
                products,
                reviews
        );
    }

    private static String[] getDetailImgs(List<Portfolio> portfolioList) {
        return portfolioList.stream()
                .map(Portfolio::getPortfolioURL)
                .limit(5)
                .toArray(String[]::new);
    }

    private static int[] getThisHolidays(Studio studio) {
        String holidays = studio.getHolidays();
        if(holidays == null) return new int[]{};

        String holiday = studio.getHolidays();
        String[] holidayStr = holiday.split("&");
        return Arrays.stream(holidayStr).mapToInt(Integer::parseInt).toArray();
    }

}