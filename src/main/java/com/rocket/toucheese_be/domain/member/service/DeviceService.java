package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.repository.DeviceRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    // 디바이스 저장 - 차후 Redis 사용 고려를 위해 테이블 분리
    public void save(Device device) {
        deviceRepository.save(device);
    }

    // member id를 통해 device 가져오기
    public Device getDeviceByMemberId(Long memberId) {
        return deviceRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DEVICE));
    }
}
