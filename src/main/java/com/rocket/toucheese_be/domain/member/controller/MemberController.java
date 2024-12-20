package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.LoginDto;
import com.rocket.toucheese_be.domain.member.dto.LoginReqDto;
import com.rocket.toucheese_be.domain.member.dto.TokenDto;
import com.rocket.toucheese_be.domain.member.entity.Token;
import com.rocket.toucheese_be.domain.member.service.AuthService;
import com.rocket.toucheese_be.domain.member.service.MemberService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(name = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final AuthService authService;
    private final MemberService memberService;

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public Response<LoginDto> login(@RequestHeader("socialId") String socialId, @RequestBody LoginReqDto loginReqDto) {
        LoginDto loginDto = authService.login(loginReqDto.socialType(), socialId);
        return Response.of(SuccessCode.LOGIN_SUCCESS, loginDto);
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/logout")
    public Response<LoginDto> logout(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("로그인되지 않은 사용자입니다.");
        }
        System.out.println("Principal: " + principal.getName()); // 디버깅 용도, 차후 삭제
        long memberId = Long.parseLong(principal.getName());
        authService.logout(memberId);
        return Response.of(SuccessCode.LOGOUT_SUCCESS);
    }

    @Operation(summary = "탈퇴", description = "탈퇴")
    @DeleteMapping("/withdrawal")
    public Response<LoginDto> withdrawal(Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        authService.withdraw(memberId);
        return Response.of(SuccessCode.GOOD_BYE_SUCCESS);
    }

    @Operation(summary = "엑세스 토큰 갱신", description = "엑세스 토큰 갱신")
    @PostMapping("/refreshAccessToken")
    public Response<TokenDto> refreshAccessToken(@RequestBody TokenDto tokenReq) {
        Token token = authService.refreshAccessToken(tokenReq.refreshToken());
        TokenDto tokenDto = TokenDto.of(token);
        return Response.of(SuccessCode.REFRESH_ACCESS_TOKEN_SUCCESS, tokenDto);
    }

    /**
     * 멤버 이름 변경
     */
    @Operation(
            summary = "멤버 이름 변경",
            description = "멤버 ID를 사용하여 이름을 변경합니다. 새로운 이름은 중복될 수 없습니다."
    )
    @PutMapping("/{memberId}/name")
    public Response<Void> updateMemberName(@PathVariable Long memberId, @RequestParam String newName) {
        memberService.updateMemberName(memberId, newName);
        return Response.of(SuccessCode.UPDATE_MEMBER_NAME_SUCCESS);
    }
}
