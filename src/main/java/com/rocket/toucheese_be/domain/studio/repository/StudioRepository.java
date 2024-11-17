package com.rocket.toucheese_be.domain.studio.repository;

import com.rocket.toucheese_be.domain.studio.dto.StudioDto;
import com.rocket.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    // 모든 스튜디오 리스트
    Page<Studio> findAll(Pageable pageable);

    // 특정 스튜디오 조회
    Optional<Studio> findStudioById(Long id);

    // 컨셉 ID에 해당하는 스튜디오 리스트
    @Query("SELECT s FROM Studio s INNER JOIN StudioConcept sc ON s.id = sc.studio.id WHERE sc.concept.id = :conceptId")
    List<StudioDto> findStudiosByConceptId(@Param("conceptId") Long conceptId);

    // 컨셉 + 평점순 정렬
    @Query("SELECT s FROM Studio s " +
            "JOIN s.studioConceptList sc " +
            "LEFT JOIN s.ratingList r " +
            "WHERE sc.concept.id = :conceptId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY COALESCE(AVG(r.rating), 0) DESC")
    List<Studio> findStudiosByConceptIdOrderByAverageRatingDesc(@Param("conceptId") Long conceptId);

    @Query("SELECT s FROM Studio s " +
            "LEFT JOIN s.studioConceptList sc " +
            "LEFT JOIN s.region r " +
            "WHERE sc.concept.id = :conceptId " +
            "AND r.id = :regionId")
    List<Studio> findStudiosByConceptIdAndRegionId(
            @Param("conceptId") Long conceptId,
            @Param("regionId") Long regionId);
}
