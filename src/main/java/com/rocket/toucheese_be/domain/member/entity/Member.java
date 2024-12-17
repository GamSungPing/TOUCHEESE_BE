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
    private String username;


    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> review;

    @Column(length = 20)
    private String nickname;

    private String profileImageUrl;

    // device와 1대1 맵핑, 멤버와 연결 끊기면 (갱신) device 삭제, 멤버 삭제시 device 삭제 (생명 주기)
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Device device;

    @Transactional
    public void setDevice(Device device) {
        this.device = device;
    }
}
