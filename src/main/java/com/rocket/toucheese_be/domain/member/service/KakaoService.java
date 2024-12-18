package com.rocket.toucheese_be.domain.member.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate;

    public String getKakaoData(String socialAccessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + socialAccessToken);

            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<KakaoUserResponse> responseData = restTemplate.postForEntity(
                    "https://kapi.kakao.com/v2/user/me",
                    httpEntity,
                    KakaoUserResponse.class
            );

            return Objects.requireNonNull(responseData.getBody()).getId();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("API 호출 실패: " + e.getStatusCode(), e);
        } catch (Exception e) {
            throw new IllegalStateException("처리 중 에러 발생", e);
        }
    }

    // Kakao 응답 DTO
    @Data
    public static class KakaoUserResponse {
        private String id; // 사용자 고유 ID
        private String connected_at; // 연결 시간
        private Map<String, Object> properties; // 사용자 프로필 정보
        private Map<String, Object> kakao_account; // 카카오 계정 정보
    }
}
