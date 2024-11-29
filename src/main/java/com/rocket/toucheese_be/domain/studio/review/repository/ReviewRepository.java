package com.rocket.toucheese_be.domain.studio.review.repository;

import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 스튜디오의 특정 리뷰 조회
    Optional<Review> findByStudioIdAndId(Long studioId, Long reviewId);

    // 특정 스튜디오의 리뷰 ID 리스트 조회
    @Query("SELECT r.id FROM Review r WHERE r.studio.id = :studioId")
    List<Long> findIdsByStudioId(@Param("studioId") Long studioId);
}
