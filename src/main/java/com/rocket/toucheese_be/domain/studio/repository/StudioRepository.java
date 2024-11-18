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
    // 모든 스튜디오 리스트
    Page<Studio> findAll(Pageable pageable);

    // 컨셉 ID에 해당하는 스튜디오 리스트
    @Query("SELECT s FROM Studio s INNER JOIN StudioConcept sc ON s.id = sc.studio.id WHERE sc.concept.id = :conceptId")
    Page<Studio> findStudiosByConceptId(@Param("conceptId") Long conceptId, Pageable pageable);

    // 컨셉 + 평점순 정렬
    @Query("SELECT s FROM Studio s " +
            "JOIN s.studioConceptList sc " +
            "LEFT JOIN s.ratingList r " +
            "WHERE sc.concept.id = :conceptId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY COALESCE(AVG(r.rating), 0) DESC")
    Page<Studio> findStudiosByConceptIdOrderByAverageRatingDesc(@Param("conceptId") Long conceptId, Pageable pageable);

    // 컨셉 + 지역 필터링
    @Query("SELECT s FROM Studio s " +
            "LEFT JOIN s.studioConceptList sc " +
            "LEFT JOIN s.region r " +
            "WHERE sc.concept.id = :conceptId " +
            "AND r.id = :regionId")
    Page<Studio> findStudiosByConceptIdAndRegionId(
            @Param("conceptId") Long conceptId,
            @Param("regionId") Long regionId,
            Pageable pageable);

    // 컨셉 + 지역 + 평점 내림차순
    @Query("SELECT s FROM Studio s " +
            "LEFT JOIN s.studioConceptList sc " +
            "LEFT JOIN s.region re " +
            "LEFT JOIN s.ratingList ra " +
            "WHERE sc.concept.id = :conceptId " +
            "AND re.id = :regionId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY COALESCE(AVG(ra.rating), 0) DESC")
    Page<Studio> findStudiosByConceptIdAndRegionIdOrderByAverageRatingDesc(@Param("conceptId") Long conceptId,
                                                                           @Param("regionId") Long regionId,
                                                                           Pageable pageable);

    // 컨셉 + 가격 오름차순 정렬
    @Query("SELECT s FROM Studio s " +
            "INNER JOIN s.studioConceptList sc " +
            "WHERE sc.concept.id = :conceptId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY s.profilePrice ASC")
    Page<Studio> findStudiosByConceptIdOrderByProfilePriceAsc(@Param("conceptId") Long conceptId, Pageable pageable);

    // 컨셉 + 지역 + 가격 오름차순 정렬
    @Query("SELECT s FROM Studio s " +
            "LEFT JOIN s.studioConceptList sc " +
            "LEFT JOIN s.region r " +
            "WHERE sc.concept.id = :conceptId " +
            "AND r.id = :regionId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY s.profilePrice ASC")
    Page<Studio> findStudiosByConceptIdAndRegionIdOrderByProfilePriceAsc(@Param("conceptId")Long conceptId,
                                                                         @Param("regionId") Long regionId,
                                                                         Pageable pageable);

    // 컨셉 + 평점 내림차순 + 가격 오름차순 정렬
    @Query("SELECT s FROM Studio s " +
            "JOIN s.studioConceptList sc " +
            "LEFT JOIN s.ratingList r " +
            "WHERE sc.concept.id = :conceptId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY COALESCE(AVG(r.rating), 0) DESC, s.profilePrice ASC")
    Page<Studio> findStudiosByConceptIdOrderByAverageRatingDescAndProfilePriceAsc(@Param("conceptId") Long conceptId, Pageable pageable);


    // 컨셉 + 지역 + 평점 내림차순 + 가격 오름차순 정렬
    @Query("SELECT s FROM Studio s " +
            "LEFT JOIN s.studioConceptList sc " +
            "LEFT JOIN s.region re " +
            "LEFT JOIN s.ratingList ra " +
            "WHERE sc.concept.id = :conceptId " +
            "AND re.id = :regionId " +
            "GROUP BY s.id, s.name " +
            "ORDER BY COALESCE(AVG(ra.rating), 0) DESC, s.profilePrice ASC")
    Page<Studio> findStudiosByConceptIdAndRegionIdOrderByAverageRatingDescAndProfilePriceAsc(@Param("conceptId")Long conceptId,
                                                                                             @Param("regionId") Long regionId,
                                                                                             Pageable pageable);
}
