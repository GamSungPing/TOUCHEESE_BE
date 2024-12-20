package com.rocket.toucheese_be.global.security.jwt;

import com.rocket.toucheese_be.domain.member.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleConverter {

    // Role -> GrantedAuthority 변환
    public static List<GrantedAuthority> toAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getKey()))
                .collect(Collectors.toList());
    }

    // 단일 Role -> GrantedAuthority 변환
    public static List<GrantedAuthority> toAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getKey()));
    }
}
