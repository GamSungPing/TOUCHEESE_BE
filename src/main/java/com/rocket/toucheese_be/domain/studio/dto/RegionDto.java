package com.rocket.toucheese_be.domain.studio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Builder
public record RegionDto(
        @JsonIgnore Long id,
        String name
) {
}

