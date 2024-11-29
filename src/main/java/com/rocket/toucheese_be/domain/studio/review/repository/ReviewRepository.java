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


    // 특정 스튜디오의 리뷰 목록 조회 (페이징)
    Page<Review> findByStudioId(Long studioId, Pageable pageable);

    // 특정 스튜디오의 특정 리뷰 조회
    Optional<Review> findByStudioIdAndId(Long studioId, Long reviewId);

    // 스튜디오 해당 상품에 관련된 리뷰 조회
    Page<Review> findByStudioIdAndProductId(Long studioId, Long productId, Pageable pageable);

}
