package com.rocket.toucheese_be.domain.studio.repository;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    List<Studio> findAll();
    Optional<Studio> findStudioById(Long id);

    @Query("SELECT s FROM Studio s INNER JOIN StudioConcept sc ON s.id = sc.studio.id WHERE sc.concept.id = :conceptId")
    List<Studio> findStudiosByConceptId(@Param("conceptId") Long conceptId);

    @Query("SELECT s FROM Studio s " +
            "JOIN s.studioConceptList sc " +
            "LEFT JOIN s.ratingList r " +
            "WHERE sc.concept.id = :conceptId " +
            "GROUP BY s.id " +
            "ORDER BY COALESCE(AVG(r.rating), 0) DESC")
    List<Studio> findStudiosByConceptIdOrderByAverageRatingDesc(@Param("conceptId") Long conceptId);
}
