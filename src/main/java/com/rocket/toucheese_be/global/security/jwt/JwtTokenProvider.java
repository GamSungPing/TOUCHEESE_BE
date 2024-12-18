package com.rocket.toucheese_be.global.security.jwt;

import com.rocket.toucheese_be.global.config.ValueConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

import static com.rocket.toucheese_be.global.security.jwt.JwtValidationType.*;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.util.Base64.getEncoder;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final ValueConfig valueConfig;

    // Authentication에서 사용자 정보 추출, 토큰 만료 시간을 설정한 다음 jwt 생성
    public String generateToken(Authentication authentication, long expiration) {
        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .and()
                .claims(generateClaims(authentication))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expiration)))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    // 사용자 정보를 추출 (Claims = JWT Payload 부분에 있는 정보)
    private Claims generateClaims(Authentication authentication) {
        return Jwts.claims()
                .add("memberId", authentication.getPrincipal())
                .build();
    }

    private SecretKey getSigningKey() {
        String encodedKey = getEncoder().encodeToString(valueConfig.getSecretKey().getBytes());
        return hmacShaKeyFor(encodedKey.getBytes());
    }

    // 토큰 검증
    public JwtValidationType validateToken(String token) {
        try {
            getBody(token);
            return VALID_JWT;
        } catch (MalformedJwtException exception) {
            log.error(exception.getMessage());
            return INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException exception) {
            log.error(exception.getMessage());
            return EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException exception) {
            log.error(exception.getMessage());
            return UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException exception) {
            log.error(exception.getMessage());
            return EMPTY_JWT;
        }
    }

    // 토큰에서 Claims 정보 추출
    private Claims getBody(final String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Claims 안에 "memberId"라는 키로 저장된 값을 Long으로 반환
    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.parseLong(claims.get("memberId").toString());
    }
}