package com.rocket.toucheese_be.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    STUDIO("ROLE_STUDIO", "스튜디오 관계자"), // 미사용
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;

    // 문자열 key를 받아서 해당하는 Role을 반환
    public static Role fromKey(String key) {
        for (Role role : Role.values()) {
            if (role.getKey().equals(key)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role key: " + key);
    }

}
