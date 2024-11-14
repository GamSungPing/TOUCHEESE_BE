package com.rocket.toucheese_be.domain.studio.dto;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudioListDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    public StudioListDto(Studio studio) {
        this.id = studio.getId();
        this.name = studio.getName();
    }
}
