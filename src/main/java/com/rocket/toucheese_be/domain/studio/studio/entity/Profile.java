package com.rocket.toucheese_be.domain.studio.studio.entity;

import jakarta.persistence.*;
        import lombok.*;

        import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(callSuper = false)
public class Profile { // TODO: 스튜디오 안에 필드로 넣을 것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    private String profileURL;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

}

