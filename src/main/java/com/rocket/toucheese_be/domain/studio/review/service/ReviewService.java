package com.rocket.toucheese_be.domain.studio.review.service;


import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDetailDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewPhotoDto;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;
import com.rocket.toucheese_be.domain.studio.review.repository.ReviewPhotoRepository;
import com.rocket.toucheese_be.domain.studio.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    // 해당 스튜디오의
    public Page<ReviewPhotoDto> getReviewImagesByStudioId(Long studioId, Pageable pageable) {
        // ReviewRepository에서 스튜디오 ID에 해당하는 모든 리뷰 ID 조회
        List<Long> reviewIds = reviewRepository.findIdsByStudioId(studioId);

        // ReviewPhotoRepository에서 해당 리뷰 ID들에 속하는 ReviewPhoto 페이징 조회
        Page<ReviewPhoto> reviewPhotoPage = reviewPhotoRepository.findByReviewIdIn(reviewIds, pageable);

        // ReviewPhoto를 ReviewPhotoDto로 변환
        return reviewPhotoPage.map(ReviewPhotoDto::from);
    }

    // 특정 스튜디오의 특정 리뷰 상세 조회
    public ReviewDetailDto getReviewDetailByStudioAndReview(Long studioId, Long reviewId) {
        Review review = reviewRepository.findByStudioIdAndId(studioId, reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스튜디오에 리뷰가 존재하지 않습니다. 스튜디오 ID: " + studioId + ", 리뷰 ID: " + reviewId));

        return ReviewDetailDto.from(review);
    }
}