package com.rocket.toucheese_be.domain.studio.review.repository;

import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {

    // 여러 리뷰 ID에 해당하는 ReviewPhoto 조회 (페이징 포함)
    Page<ReviewPhoto> findByReviewIdIn(List<Long> reviewIds, Pageable pageable);

}
