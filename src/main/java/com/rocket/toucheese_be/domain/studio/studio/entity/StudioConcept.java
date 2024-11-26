package com.rocket.toucheese_be.domain.studio.studio.entity;

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
    @Column(name = "studio_concept_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id")
    private Concept concept;

}
