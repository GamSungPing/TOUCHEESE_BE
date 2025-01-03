package com.rocket.toucheese_be.domain.member.service;

import com.rocket.toucheese_be.domain.member.dto.DeviceRegisterDto;
import com.rocket.toucheese_be.domain.member.entity.Device;
import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.member.repository.DeviceRepository;
import com.rocket.toucheese_be.domain.member.repository.MemberRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, String> redisTemplate; // RedisTemplate 추가

    private static final Duration TOKEN_EXPIRATION_TIME = Duration.ofDays(200);


    @Transactional
    public Response<Device> registerDevice(DeviceRegisterDto deviceRegisterDto) {
        Member member = memberRepository.findById(deviceRegisterDto.memberId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Optional<Device> existingDevice = Optional.ofNullable(member.getDevice());

        if (existingDevice.isPresent()) {
            Device device = existingDevice.get();
            if (!device.getDeviceToken().equals(deviceRegisterDto.deviceToken())) {
                device.setDeviceToken(deviceRegisterDto.deviceToken());
                deviceRepository.save(device);

                // Redis에 디바이스 토큰 업데이트
                String redisKey = getRedisKeyForMember(member.getId());
                redisTemplate.opsForValue().set(redisKey, deviceRegisterDto.deviceToken(), TOKEN_EXPIRATION_TIME);

                return Response.of(SuccessCode.UPDATE_DEVICE_TOKEN_SUCCESS);
            } else {
                return Response.of(SuccessCode.NOT_UPDATE_DEVICE_TOKEN_SUCCESS);
            }
        } else {
            Device device = Device.builder()
                    .member(member)
                    .deviceToken(deviceRegisterDto.deviceToken())
                    .build();
            deviceRepository.save(device);
            member.setDevice(device);
            memberRepository.save(member);

            // Redis에 디바이스 토큰 저장
            String redisKey = getRedisKeyForMember(member.getId());
            redisTemplate.opsForValue().set(redisKey, deviceRegisterDto.deviceToken(), TOKEN_EXPIRATION_TIME);


            return Response.of(SuccessCode.GET_DEVICE_TOKEN_SUCCESS);
        }
    }
    // 토큰이 만료되었거나 Redis의 저장된 토큰이 없을 경우 예외를 던지지 않고 null을 반환
    @Transactional
    public String getDeviceTokenFromRedis(Long memberId) {
        String token = redisTemplate.opsForValue().get(getRedisKeyForMember(memberId));

        // Redis에 저장된 토큰이 없을 경우 null 반환
        if (token == null) {
            return null;  // 예외 처리 대신 null 반환
        }

        // Redis에서 TTL을 확인하여, 토큰이 만료되었는지 확인
        Long ttl = redisTemplate.getExpire(getRedisKeyForMember(memberId), TimeUnit.SECONDS); // TTL을 초 단위로 확인

        if (ttl != null && ttl <= 0) {
            // TTL이 0 이하이면 만료된 토큰으로 간주
            return null;  // 만료된 토큰은 null 반환
        }

        return token;
    }

    // Custom 메소드: memberId 값을 이용한 키 생성
    public String getRedisKeyForMember(Long memberId) {
        return "memberId:" + memberId;
    }



}

