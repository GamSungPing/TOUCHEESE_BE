package com.rocket.toucheese_be.domain.studio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.concept.entity.Concept;
import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
public class StudioConcept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Studio studio;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Concept concept;
}
