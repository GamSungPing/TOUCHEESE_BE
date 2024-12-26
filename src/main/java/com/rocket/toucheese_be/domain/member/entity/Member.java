package com.rocket.toucheese_be.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.like.entity.Like;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Column(length = 20, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> review;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> favorites = new ArrayList<>();

    // device와 1대1 맵핑, 멤버와 연결 끊기면 (갱신) device 삭제, 멤버 삭제시 device 삭제 (생명 주기)
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Device device;

    @JsonIgnore
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    @JsonIgnore
    private String socialId;

    @JsonIgnore
    private String refreshToken; // TODO: 테이블 분리해 member와 OneToOne 관계 설정 고려?

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

    @Transactional
    public void setName(String name) {
        this.name = name;
    }
}
