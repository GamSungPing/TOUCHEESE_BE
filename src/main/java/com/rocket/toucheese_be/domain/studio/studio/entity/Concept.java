package com.rocket.toucheese_be.domain.studio.studio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concept_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "concept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudioConcept> studioConceptList;

    public String getMainUrl(Long id) {
        return ConceptMainUrl.findById(id).getMainUrl();
    }
}
