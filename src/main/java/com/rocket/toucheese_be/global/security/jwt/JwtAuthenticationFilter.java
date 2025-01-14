package com.rocket.toucheese_be.global.security.jwt;

import com.rocket.toucheese_be.domain.member.entity.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.rocket.toucheese_be.global.security.jwt.JwtValidationType.VALID_JWT;
import static io.jsonwebtoken.lang.Strings.hasText;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_HEADER = "Bearer ";
    private static final String BLANK = "";

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${admin.name}")
    private String adminName;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        // 1. 토큰이 유효하다면 jwt을 분석하여 사용자 식별자 추출
        // 2. 이를 기반으로 한 UserAuthentication 객체 생성
        // 3. 이 객체를 현재 보안 컨텍스트에 설정하여 애플리케이션 내의 다른 부분에서 현재 인증된 사용자에 대한 정보를 사용할 수 있도록 함
        try {
            String token = getAccessTokenFromRequest(request);
            if (hasText(token) && jwtTokenProvider.validateToken(token) == VALID_JWT) {

                List<String> roles = jwtTokenProvider.getRolesFromJwt(token); // JWT에서 역할을 추출하는 메서드 호출

                List<GrantedAuthority> authorities = roles.stream()
                        .map(Role::fromKey)  // 역할을 Role 객체로 변환
                        .flatMap(role -> RoleConverter.toAuthorities(role).stream())
                        .toList();

                UserAuthentication authentication = new UserAuthentication(getMemberId(token), null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                HttpSession session = request.getSession(false);

                if (session != null && Boolean.TRUE.equals(session.getAttribute("isAdmin"))) {
                    // 세션에서 `isAdmin`을 확인하여 Security Context에 권한 추가
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    adminName, // 사용자의 이름 또는 식별자
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessTokenFromRequest(HttpServletRequest request) {
        return isContainsAccessToken(request) ? getAuthorizationAccessToken(request) : null;
    }

    private boolean isContainsAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        return authorization != null && authorization.startsWith(BEARER_HEADER);
    }

    private String getAuthorizationAccessToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION).replaceFirst(BEARER_HEADER, BLANK);
    }

    private long getMemberId(String token) {
        return jwtTokenProvider.getUserFromJwt(token);
    }
}