package com.rocket.toucheese_be.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(callSuper = false)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // username으로 변경 염두

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> review;

    @Column(length = 20)
    private String nickname;

    private String profileImageUrl;

    // device와 1대1 맵핑, 멤버와 연결 끊기면 (갱신) device 삭제, 멤버 삭제시 device 삭제 (생명 주기)
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Device device;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    private String refreshToken; // TODO: 따로 테이블 만들어서 member와 OneToOne 관계로 설정

    @Builder
    public Member(SocialType socialType, String socialId) {
        this.socialType = socialType;
        this.socialId = socialId;
    }

    @Transactional
    public void setDevice(Device device) {
        this.device = device;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void resetRefreshToken() {
        this.refreshToken = null;
    }
}
