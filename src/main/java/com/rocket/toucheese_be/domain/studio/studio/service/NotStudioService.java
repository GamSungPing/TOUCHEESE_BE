package com.rocket.toucheese_be.domain.studio.studio.service;

import com.rocket.toucheese_be.domain.studio.studio.entity.Concept;
import com.rocket.toucheese_be.domain.studio.studio.repository.ConceptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotStudioService {
    private final ConceptRepository conceptRepository;

    public List<Concept> getAllConcept() {
        return conceptRepository.findAll();
    }

    public Concept getConceptById(Long id) {
        return conceptRepository.findById(id).orElse(null);
    }
}