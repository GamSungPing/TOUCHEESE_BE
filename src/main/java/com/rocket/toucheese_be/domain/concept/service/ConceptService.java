package com.rocket.toucheese_be.domain.concept.service;

import com.rocket.toucheese_be.domain.concept.entity.Concept;
import com.rocket.toucheese_be.domain.concept.repository.ConceptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConceptService {
    private final ConceptRepository conceptRepository;

    public List<Concept> getAllConcepts() {
        return conceptRepository.findAll();
    }

    public Concept getConcept(Long id) {
        return conceptRepository.findConceptById(id)
                .orElseThrow(() -> new RuntimeException("그런 컨셉 없음"));
    }
}
