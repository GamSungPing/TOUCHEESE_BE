package com.rocket.toucheese_be.domain.studio.dto;

import com.rocket.toucheese_be.domain.studio.entity.Concept;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ConceptListDto(
        Long id,
        String name,
        String mainUrl
) {
    public ConceptListDto(Concept concept) {
        this(
                concept.getId(),
                concept.getName(),
                concept.getMainUrl(concept.getId())
        );
    }

    public static List<ConceptListDto> fromConceptList(List<Concept> conceptList) {
        return Optional.ofNullable(conceptList)
                .orElse(Collections.emptyList())
                .stream()
                .map(ConceptListDto::new)
                .toList();
    }
}
