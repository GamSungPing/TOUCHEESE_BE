package com.rocket.toucheese_be.domain.studio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.REMOVE)
    private List<StudioConcept> studioConceptList;

    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
    private List<Rating> ratingList;
}
