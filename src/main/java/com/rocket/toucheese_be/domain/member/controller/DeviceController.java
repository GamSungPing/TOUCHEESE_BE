package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.DeviceRegisterDto;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.service.DeviceService;
import com.rocket.toucheese_be.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
