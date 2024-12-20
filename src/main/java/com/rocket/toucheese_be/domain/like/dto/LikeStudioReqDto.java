package com.rocket.toucheese_be.domain.like.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

public record LikeStudioReqDto(
        Long memberId,
        Long studioId,

        @JsonIgnore
        LocalDateTime createdAt
) {
    // 생성자가 호출될 때, createdAt을 자동으로 현재 시간으로 설정
    public LikeStudioReqDto(Long memberId, Long studioId) {
        this(memberId, studioId, LocalDateTime.now());  // createdAt을 기본값으로 설정
    }
}
