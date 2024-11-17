package com.rocket.toucheese_be.domain.studio.dto;

import com.rocket.toucheese_be.domain.studio.entity.Rating;
import com.rocket.toucheese_be.domain.studio.entity.Region;
import com.rocket.toucheese_be.domain.studio.entity.Studio;

public record StudioDto(
        Long id,
        String name,
        Double averageRating,
        RegionDto region
) {
    public static StudioDto fromEntity(Studio studio) {
        return new StudioDto(
                studio.getId(),
                studio.getName(),
                studio.getRating(),
                studio.getRegion() != null
                        ? RegionDto.builder()
                        .id(studio.getRegion().getId())
                        .name(studio.getRegion().getName())
                        .build()
                        :null

        );
    }
}
