package com.rocket.toucheese_be.domain.studio.repository;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    // 컨셉 ID에 해당하는 스튜디오 리스트
    @Query("SELECT s FROM Studio s INNER JOIN StudioConcept sc ON s.id = sc.studio.id WHERE sc.concept.id = :conceptId")
    Page<Studio> findStudiosByConceptId(@Param("conceptId") Long conceptId, Pageable pageable);

}
