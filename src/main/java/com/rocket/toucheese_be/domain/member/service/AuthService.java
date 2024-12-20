package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.dto.LoginDto;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.entity.Role;
import com.rocket.toucheese_be.domain.member.entity.SocialType;
import com.rocket.toucheese_be.domain.member.entity.Token;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import com.rocket.toucheese_be.global.security.jwt.JwtTokenProvider;
import com.rocket.toucheese_be.global.security.jwt.RoleConverter;
import com.rocket.toucheese_be.global.security.jwt.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.rocket.toucheese_be.global.security.jwt.JwtValidationType.VALID_JWT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private static final int ACCESS_TOKEN_EXPIRATION = 7200000; // 2시간
    private static final int REFRESH_TOKEN_EXPIRATION = 1209600000; // 14일

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    // 로그인
    @Transactional
    public LoginDto login(SocialType socialType, String socialId) {
        Member member = getMember(socialType, socialId);
        Token token = getToken(member);
        System.out.println("역할 여 있네~~~ "+member.getRole());
        return LoginDto.of(token, member.getId(), member.getName());
    }

    // 로그아웃
    @Transactional
    public void logout(long memberId) {
        Member member = findMember(memberId);
        member.resetRefreshToken();
    }

    // 탈퇴
    @Transactional
    public void withdraw(long memberId) {
        Member member = findMember(memberId);
        deleteMember(member);
    }

    private Member getMember(SocialType socialType, String socialId) {
        return signupOrLogin(socialType, socialId);
    }

    private Member signupOrLogin(SocialType socialType, String socialId) {
        return memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
                .orElseGet(() -> saveMember(socialType, socialId));
    }

    private Member saveMember(SocialType socialType, String socialId) {
        long hashValue = socialId.hashCode();
        String hashStr = String.valueOf(hashValue).substring(0, 6);
        Member member = Member.builder()
                .socialType(socialType)
                .socialId(socialId)
                .name(socialType+"_"+hashStr)
                .role(Role.USER) // ADMIN은 DB에서 직접 추가
                .build(); // TODO: device 토큰 회원가입/로그인 때 받을지 고민할 것
        return memberRepository.save(member);
    }

    private Token getToken(Member member) {
        List<GrantedAuthority> authorities = RoleConverter.toAuthorities(member.getRole());
        Token token = generateToken(new UserAuthentication(member.getId(), null, authorities));
        member.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private Token generateToken(Authentication authentication) {
        return Token.builder()
                .accessToken(jwtTokenProvider.generateToken(authentication, ACCESS_TOKEN_EXPIRATION))
                .refreshToken(jwtTokenProvider.generateToken(authentication, REFRESH_TOKEN_EXPIRATION))
                .build();
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
    }

    @Transactional
    public Token refreshAccessToken(String refreshToken) {
        // Refresh Token 유효성 검증
        if (jwtTokenProvider.validateToken(refreshToken) != VALID_JWT) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN, "리프레시 토큰이 유효하지 않습니다. 다시 로그인 하세요");
        }

        // Refresh Token으로 사용자 정보 추출
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
        Long memberId = Long.parseLong(authentication.getName()); // User ID 추출

        // 사용자 조회 및 저장된 Refresh Token 확인
        Member member = findMember(memberId);
        if (!refreshToken.equals(member.getRefreshToken())) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        // 새로운 Access Token 생성
        String newAccessToken = jwtTokenProvider.generateToken(authentication, ACCESS_TOKEN_EXPIRATION);

        return Token.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken) // 기존 Refresh Token 그대로 반환
                .build();
    }

    private void deleteMember(Member member) {
        memberRepository.delete(member);
    }
}
