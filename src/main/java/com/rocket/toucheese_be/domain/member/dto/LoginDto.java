package com.rocket.toucheese_be.domain.member.dto;

import com.rocket.toucheese_be.domain.member.entity.Token;
import lombok.Builder;
import lombok.NonNull;


@Builder
public record LoginDto(
        @NonNull String accessToken,
        @NonNull String refreshToken
) {
    public static LoginDto of(Token token) {
        return LoginDto.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
