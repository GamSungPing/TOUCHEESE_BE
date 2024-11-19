package com.rocket.toucheese_be.domain.studio.dto;

import com.rocket.toucheese_be.domain.studio.entity.Studio;

public record StudioDto(
        Long id,
        String name,
        int profilePrice,
        String priceCategory,
        Double rating,
        RegionDto region
) {
    public static StudioDto fromEntity(Studio studio) {
        return new StudioDto(
                studio.getId(),
                studio.getName(),
                studio.getProfilePrice(),
                studio.getPriceCategory(),
                studio.calculateAverageRating(),
                studio.getRegion() != null
                        ? RegionDto.builder()
                        .id(studio.getRegion().getId())
                        .name(studio.getRegion().getName())
                        .build()
                        :null
        );
    }
}
