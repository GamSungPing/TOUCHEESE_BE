package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.DeviceRegisterDto;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.service.DeviceService;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
@RestController
public class DeviceController {
    private final DeviceService deviceService;

    // 푸시 알림에 대해 사용자가 승낙할 때 호출되어야 함 (디바이스 토큰 등록)
    // 한 멤버가 하나의 디바이스만 가지는 것으로 가정
    @Operation(
            summary = "디바이스 토큰 등록",
            description = "푸시 알림에 대해 사용자가 승낙할 때 DeviceRegisterDto와 함께 호출되어야 합니다")
    @PostMapping("/register")
    public Response<Device> registerDevice(@RequestBody DeviceRegisterDto deviceRegisterDto) {
        return deviceService.registerDevice(deviceRegisterDto);
    }

    @Operation(
            summary = "Redis에서 디바이스 토큰 조회",
            description = "회원 ID로 Redis에서 저장된 디바이스 토큰을 조회합니다.")
    @GetMapping("/token/{memberId}")
    public Response<String> getDeviceTokenFromRedis(@PathVariable Long memberId) {
        String token = deviceService.getDeviceTokenFromRedis(memberId);
        if (token == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_DEVICE_TOKEN_IN_REDIS);
        }
        return Response.of(SuccessCode.GET_DEVICE_TOKEN_SUCCESS, token);
    }
}
