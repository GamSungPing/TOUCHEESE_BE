package com.rocket.toucheese_be.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate;

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.kakao.token-uri}")
    private String tokenUri;

//    public KakaoTokenDto getKakaoToken(String authorizationCode) {
//
//        // 요청 파라미터 설정
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("grant_type", "authorization_code");
//        parameters.add("client_id", clientId);
//        parameters.add("redirect_uri", redirectUri);
//        parameters.add("code", authorizationCode);
//
//        // 헤더 타입 명시 - application/x-www-form-urlencoded
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);
//
//        // HTTP POST 요청
//        ResponseEntity<KakaoTokenDto> response = restTemplate.postForEntity(
//                tokenUri,
//                requestEntity,
//                KakaoTokenDto.class
//        );
//        System.out.println(response.getBody().getAccessToken() + " 이게 토큰이지");
//
//        // 응답 결과 반환
//        return response.getBody();
//    }

    // 카카오에 접촉하여 user 정보를 받아오는 로직
//    public String getKakaoData(String socialAccessToken) {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", socialAccessToken);
//            System.out.println("여는 잘 되는겨 "+socialAccessToken);
//            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
//            ResponseEntity<KakaoUserResponse> responseData = restTemplate.postForEntity(
//                    "https://kapi.kakao.com/v2/user/me",
//                    httpEntity,
//                    KakaoUserResponse.class
//            );
//            return Objects.requireNonNull(responseData.getBody()).getId();
//        } catch (HttpClientErrorException e) {
//            String errorResponse = e.getResponseBodyAsString();
//            throw new RuntimeException("API 호출 실패: " + e.getStatusCode() + " - " + errorResponse, e);
//        } catch (Exception e) {
//            throw new IllegalStateException("처리 중 에러 발생", e);
//        }
//    }

    // Kakao 응답 DTO
//    @Data
//    public static class KakaoUserResponse {
//        private String id; // 사용자 고유 ID
//        @JsonProperty("connected_at")
//        private String connectedAt; // 연결 시간
//        private Map<String, Object> properties; // 사용자 프로필 정보
//        private Map<String, Object> kakao_account; // 카카오 계정 정보
//    }
}
