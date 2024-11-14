package com.rocket.toucheese_be.domain.concept.repository;

import com.rocket.toucheese_be.domain.concept.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Long> {
    List<Concept> findAll();
    Optional<Concept> findConceptById(Long id);
}
