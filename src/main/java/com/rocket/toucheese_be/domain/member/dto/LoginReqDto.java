package com.rocket.toucheese_be.domain.member.dto;

import com.rocket.toucheese_be.domain.member.entity.SocialType;
import lombok.NonNull;

public record LoginReqDto(
        @NonNull SocialType socialType
        ) {
    public static LoginReqDto of(SocialType socialType) {
        return new LoginReqDto(socialType);
    }
}
