package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.dto.DeviceRegisterDto;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.DeviceRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final MemberService memberService;

    // 디바이스 저장 - 차후 Redis 사용 고려를 위해 테이블 분리
    @Transactional
    public void save(Device device) {
        deviceRepository.save(device);
    }

    // member id를 통해 device 가져오기
    @Transactional
    public Device getDeviceByMemberId(Long memberId) {
        return deviceRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DEVICE));
    }

    @Transactional
    public Response<Device> registerDevice(DeviceRegisterDto deviceRegisterDto) {
        Member member = memberService.findById(deviceRegisterDto.memberId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Optional<Device> existingDevice = Optional.ofNullable(member.getDevice());

        if (existingDevice.isPresent()) {
            // 해당 멤버에 device token 이미 있는 경우
            Device device = existingDevice.get();
            if(!device.getDeviceToken().equals(deviceRegisterDto.deviceToken())) {
                // 기존 토큰과 새로운 토큰이 다른 경우
                device.setDeviceToken(deviceRegisterDto.deviceToken()); // 토큰 갱신
                save(device);
                return Response.of(SuccessCode.UPDATE_DEVICE_TOKEN_SUCCESS);
            }
            else {
                return Response.of(SuccessCode.NOT_UPDATE_DEVICE_TOKEN_SUCCESS);
            }
        } else {
            // 새로 만드는 경우
            Device device = Device.builder()
                    .member(member)
                    .deviceToken(deviceRegisterDto.deviceToken())
                    .build();
            save(device);
            member.setDevice(device);
            memberService.save(member);
            return Response.of(SuccessCode.GET_DEVICE_TOKEN_SUCCESS);
        }
    }
}
