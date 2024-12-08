package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.dto.DeviceRegisterDto;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.service.DeviceService;
import com.rocket.toucheese_be.domain.member.service.MemberService;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
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
    private final MemberService memberService;

    // 푸시 알림에 대해 사용자가 승낙할 때 호출되어야 함 (디바이스 토큰 등록)
    // TODO: 토큰 유효성 체크해서 갱신하는 로직 필요
    @Operation(
            summary = "디바이스 토큰 등록",
            description = "푸시 알림에 대해 사용자가 승낙할 때 DeviceRegisterDto와 함께 호출되어야 합니다")
    @PostMapping("/register")
    public Response<Device> registerDevice(@RequestBody DeviceRegisterDto deviceRegisterDto) {
        Member member = memberService.findById(deviceRegisterDto.memberId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Device device = Device.builder()
                .member(member)
                .deviceToken(deviceRegisterDto.deviceToken())
                .build();
        deviceService.save(device);
        return Response.of(SuccessCode.GET_DEVICE_TOKEN_SUCCESS);
    }
}
