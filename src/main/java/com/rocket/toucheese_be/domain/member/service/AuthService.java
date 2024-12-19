package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.dto.LoginDto;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.entity.SocialType;
import com.rocket.toucheese_be.domain.member.entity.Token;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.global.security.jwt.JwtTokenProvider;
import com.rocket.toucheese_be.global.security.jwt.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private static final int ACCESS_TOKEN_EXPIRATION = 7200000;
    private static final int REFRESH_TOKEN_EXPIRATION = 1209600000;

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
//    private final AppleService appleService;
//    private final KakaoService kakaoService;

    @Transactional
    public LoginDto login(SocialType socialType, String socialId) {
        Member member = getMember(socialType, socialId);
        Token token = getToken(member);
        return LoginDto.of(token, member.getId());
    }

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

//    private String getSocialId(String socialAccessToken, LoginReqDto loginReqDto) {
//        return switch (loginReqDto.socialType()) {
//            case APPLE -> appleService.getAppleData(socialAccessToken);
//            case KAKAO -> kakaoService.getKakaoData(socialAccessToken);
//        };
//    }

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
                .build(); // TODO: device 토큰 회원가입/로그인 때 받을지 고민할 것
        return memberRepository.save(member);
    }

    private Token getToken(Member member) {
        Token token = generateToken(new UserAuthentication(member.getId(), null, null));
        member.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private Token generateToken(Authentication authentication) {
        return Token.builder()
                .accessToken(jwtTokenProvider.generateToken(authentication, ACCESS_TOKEN_EXPIRATION))
                .refreshToken(jwtTokenProvider.generateToken(authentication, REFRESH_TOKEN_EXPIRATION))
                .build();
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow();
    }

    private void deleteMember(Member member) {
        memberRepository.delete(member);
    }
}
