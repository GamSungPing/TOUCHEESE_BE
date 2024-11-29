package com.rocket.toucheese_be.domain.studio.review.service;


import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDetailDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import com.rocket.toucheese_be.domain.studio.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 특정 스튜디오의 리뷰 목록 조회 (대표 사진 포함)
    public Page<ReviewDto> getReviewsWithFirstPhotoByStudioId(Long studioId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByStudioId(studioId, pageable);

        return reviews.map(review -> {
            // 리뷰에 사진이 있는 경우, 첫 번째 사진을 가져옵니다.
            String photoUrl = review.getReviewPhotos().isEmpty() ? null
                    : review.getReviewPhotos().get(0).getPhotoUrl();
            return new ReviewDto(review.getId(), photoUrl);
        });
    }

    // 특정 스튜디오의 특정 리뷰 상세 조회
    public ReviewDetailDto getReviewDetailByStudioAndReview(Long studioId, Long reviewId) {
        Review review = reviewRepository.findByStudioIdAndId(studioId, reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스튜디오에 리뷰가 존재하지 않습니다. 스튜디오 ID: " + studioId + ", 리뷰 ID: " + reviewId));

        return ReviewDetailDto.from(review);
    }


    public Page<ReviewDto> getReviewsByStudioIdAndProductId(Long studioId, Pageable pageable, Long productId) {
        // ReviewRepository에서 studioId와 productId를 기준으로 리뷰 목록을 가져옵니다.
        Page<Review> reviews = reviewRepository.findByStudioIdAndProductId(studioId, productId, pageable);

        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("해당 상품의 리뷰가 존재하지 않습니다.");
        }

        // Review 엔티티를 ReviewDto로 변환하여 반환합니다.
        return reviews.map(review -> {
            // 리뷰에 사진이 있는 경우, 첫 번째 사진 URL을 가져옵니다.
            String photoUrl = review.getReviewPhotos().isEmpty()
                    ? null
                    : review.getReviewPhotos().get(0).getPhotoUrl();
            return new ReviewDto(review.getId(), photoUrl);
        });
    }
}