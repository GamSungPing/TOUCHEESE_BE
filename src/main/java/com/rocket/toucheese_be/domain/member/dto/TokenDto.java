package com.rocket.toucheese_be.domain.member.dto;

import com.rocket.toucheese_be.domain.member.entity.Token;
import lombok.NonNull;

public record TokenDto(
        @NonNull String accessToken,
        @NonNull String refreshToken
) {
    public static TokenDto of(Token token) {
        return new TokenDto(token.getAccessToken(), token.getRefreshToken());
    }
}
