package com.rocket.toucheese_be.domain.studio.entity;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(callSuper = false)
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String portfolioURL; // 이미지 URL을 저장 (S3 등에서 접근 가능한 경로)

    private String profileURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

}
