package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.LoginDto;
import com.rocket.toucheese_be.domain.member.dto.LoginReqDto;
import com.rocket.toucheese_be.domain.member.service.AuthService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final AuthService authService;

    @PostMapping("/login")
    public Response<LoginDto> login(@RequestHeader("socialId") String socialId, @RequestBody LoginReqDto loginReqDto) {
        LoginDto loginDto = authService.login(loginReqDto.socialType(), socialId);
        return Response.of(SuccessCode.LOGIN_SUCCESS, loginDto);
    }

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

    @DeleteMapping("/withdrawal")
    public Response<LoginDto> withdrawal(Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        authService.withdraw(memberId);
        return Response.of(SuccessCode.GOOD_BYE_SUCCESS);
    }
}
