package com.rocket.toucheese_be.domain.studio.studio.dto;

import com.rocket.toucheese_be.domain.studio.studio.entity.Portfolio;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;

import java.util.List;

public record StudioListDto(
        Long id,
        String name,
        int profilePrice,
        Double rating,
        List<String> portfolioUrls,
        String profileURL
) {
    public StudioListDto(Studio studio) {
        this(
                studio.getId(),
                studio.getName(),
                studio.getProfilePrice(),
                studio.getRating(),
                studio.getPortfolios().stream()
                        .map(Portfolio::getPortfolioURL)
                        .toList(), // Java 16+의 `toList()` 사용
                studio.getProfileImage().getProfileURL()
        );
    }

    public StudioListDto() {
        this(
                null, null, -1, null, null, null
        );
    }
}
