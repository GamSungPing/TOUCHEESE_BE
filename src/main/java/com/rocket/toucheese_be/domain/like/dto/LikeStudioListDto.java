package com.rocket.toucheese_be.domain.like.dto;

import com.rocket.toucheese_be.domain.studio.studio.entity.Portfolio;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;

import java.util.List;

public record LikeStudioListDto(
        Long id,
        String name,
        int profilePrice,
        Double rating,
        List<String> portfolioUrls,
        String profileURL,
        int reviewCount
) {
    public LikeStudioListDto(Studio studio) {
        this(
                studio.getId(),
                studio.getName(),
                studio.getProfilePrice(),
                studio.calculateAverageRating(),
                studio.getPortfolios().stream()
                        .map(Portfolio::getPortfolioURL)
                        .limit(5) // 최대 5개로 제한
                        .toList(),
                studio.getProfileImage().getProfileURL(),
                studio.getReviewList().size()
        );
    }
}

