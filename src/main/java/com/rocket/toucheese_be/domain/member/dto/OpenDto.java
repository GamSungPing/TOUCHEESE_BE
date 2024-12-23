package com.rocket.toucheese_be.domain.member.dto;

import com.rocket.toucheese_be.domain.member.entity.Token;
import lombok.Builder;

@Builder
public record OpenDto (
    String accessToken,
    String memberName,
    Long memberId
) {
        public static OpenDto of(Token token, String memberName, Long memberId){
            return OpenDto.builder()
                    .accessToken(token.getAccessToken())
                    .memberId(memberId)
                    .memberName(memberName)
                    .build();
        }
    }

