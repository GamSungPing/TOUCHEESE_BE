package com.rocket.toucheese_be.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member 클래스의 device 필드가 외래 키 소유 / 관리
    @OneToOne(mappedBy = "device")
    private Member member;

    private String deviceToken;  // FCM 디바이스 토큰
}
