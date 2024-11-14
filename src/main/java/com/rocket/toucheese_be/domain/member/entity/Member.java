package com.rocket.toucheese_be.domain.member.entity;

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
    private Long id;

    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
    private List<Rating> rating;
}
