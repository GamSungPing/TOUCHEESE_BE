package com.rocket.toucheese_be.domain.studio.dto;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class StudioDto {
    private Long id;
    private String name;
    private Double averageRating;

    public StudioDto(Studio studio) {
        this.id = studio.getId();
        this.name = studio.getName();
        this.averageRating = studio.getAverageRating();
    }
}
