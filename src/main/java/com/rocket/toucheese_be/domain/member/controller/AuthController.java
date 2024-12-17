package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.LoginDto;
import com.rocket.toucheese_be.domain.member.dto.LoginReqDto;
import com.rocket.toucheese_be.domain.member.service.AuthService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public Response<LoginDto> login(@RequestHeader("Authorization") String socialAccessToken, @RequestBody LoginReqDto loginReqDto) {
        LoginDto response = authService.login(socialAccessToken, loginReqDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_SUCCESS, response);
    }

    @PostMapping("/logout")
    public Response<LoginDto> logout(Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        authService.logout(memberId);
        return Response.of(SuccessCode.GET_STUDIO_LIST_SUCCESS, null);
    }

    @DeleteMapping
    public Response<LoginDto> withdrawal(Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        authService.withdraw(memberId);
        return Response.of(SuccessCode.GET_STUDIO_LIST_SUCCESS, null);
    }
}
