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

import java.util.Optional;

@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
@Controller
public class DeviceController {
    private final DeviceService deviceService;
    private final MemberService memberService;

    // 푸시 알림에 대해 사용자가 승낙할 때 호출되어야 함 (디바이스 토큰 등록)
    // TODO: 토큰 유효성 체크해서 갱신하는 로직 필요
    // 1. 타임 스탬프 - 사용 시마다 타임 스탬프 갱신, 미사용 100일 경과 후 삭제
    // 2. 정기적으로 클라이언트에서 토큰 유효성 검사해서 서버에 결과 전송
    // 한 멤버가 하나의 디바이스만 가지는 것으로 가정
    // 토큰 무효화 조건 1)앱 삭제 2)토큰 기간 만료(100일로 가정) 3)앱 데이터 초기화(캐시 및 저장 데이터 삭제)
    @Operation(
            summary = "디바이스 토큰 등록",
            description = "푸시 알림에 대해 사용자가 승낙할 때 DeviceRegisterDto와 함께 호출되어야 합니다")
    @PostMapping("/register")
    public Response<Device> registerDevice(@RequestBody DeviceRegisterDto deviceRegisterDto) {
        Member member = memberService.findById(deviceRegisterDto.memberId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Optional<Device> existingDevice = Optional.ofNullable(member.getDevice());

        if (existingDevice.isPresent()) {
            // 해당 멤버에 device token 이미 있는 경우
            Device device = existingDevice.get();
            device.setDeviceToken(deviceRegisterDto.deviceToken()); // 토큰 갱신
            deviceService.save(device);
            return Response.of(SuccessCode.UPDATE_DEVICE_TOKEN_SUCCESS);
        } else {
            // 새로 만드는 경우
            Device device = Device.builder()
                    .member(member)
                    .deviceToken(deviceRegisterDto.deviceToken())
                    .build();
            deviceService.save(device);
            return Response.of(SuccessCode.GET_DEVICE_TOKEN_SUCCESS);
        }
    }
}
