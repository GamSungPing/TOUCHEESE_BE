package com.rocket.toucheese_be.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.studio.entity.Rating;
import jakarta.persistence.*;
import lombok.*;

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

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Rating> rating;

    @Column(length = 10)
    private String nickname;
}
