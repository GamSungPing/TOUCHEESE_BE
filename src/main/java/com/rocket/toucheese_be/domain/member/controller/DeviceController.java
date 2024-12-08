package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.DeviceRegisterDto;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.service.DeviceService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
@Controller
public class DeviceController {

    private final DeviceService deviceService;

    // 푸시 알림에 대해 사용자가 승낙할 때 호출되어야 함 (디바이스 토큰 등록)
    @PostMapping("/register")
    public Response<Device> registerDevice(@RequestBody DeviceRegisterDto deviceRegisterDto) {
        Device device = new Device();
        device.setMemberId(deviceRegisterDto.memberId());
        device.setDeviceToken(deviceRegisterDto.deviceToken());

        deviceService.save(device);

        return Response.of(SuccessCode.GET_DEVICE_TOKEN_SUCCESS);
    }
}
