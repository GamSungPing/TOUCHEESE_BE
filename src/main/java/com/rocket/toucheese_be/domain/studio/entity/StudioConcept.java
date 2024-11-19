package com.rocket.toucheese_be.domain.studio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Data
public class StudioConcept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id")
    private Concept concept;
}
