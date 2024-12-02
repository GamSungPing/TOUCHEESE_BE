package com.rocket.toucheese_be.domain.studio.review.service;


import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDetailDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import com.rocket.toucheese_be.domain.studio.review.entity.ReviewPhoto;
import com.rocket.toucheese_be.domain.studio.review.repository.ReviewRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 특정 스튜디오의 리뷰 목록 조회 (대표 사진 포함)
    public Page<ReviewDto> getReviewsWithFirstPhotoByStudioId(Long studioId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByStudioId(studioId, pageable);

        if (!reviews.hasContent()) {
            throw new CustomException(ErrorCode.NOT_FOUND_REVIEW);
        }

        return reviews.map(review -> new ReviewDto(
                review.getId(),
                getFirstPhotoUrl(review)
        ));
    }

    // 특정 스튜디오의 특정 리뷰 상세 조회
    public ReviewDetailDto getReviewDetailByStudioAndReview(Long studioId, Long reviewId) {
        Review review = reviewRepository.findByStudioIdAndId(studioId, reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEW));


        return ReviewDetailDto.from(review);
    }


    // 상품
    public Page<ReviewDto> getReviewsByStudioIdAndProductId(Long studioId, Pageable pageable, Long productId) {
        // ReviewRepository에서 studioId와 productId를 기준으로 리뷰 목록을 가져옵니다.
        Page<Review> reviews = reviewRepository.findByStudioIdAndProductId(studioId, productId, pageable);

        if (!reviews.hasContent()) {
            throw new CustomException(ErrorCode.NOT_FOUND_PRODUCT_REVIEW);
        }

        return reviews.map(review -> new ReviewDto(
                review.getId(),
                getFirstPhotoUrl(review)
        ));
    }

    private String getFirstPhotoUrl(Review review) {
        return review.getReviewPhotos().stream()
                .findFirst()
                .map(ReviewPhoto::getPhotoUrl)
                .orElse(null);
    }
}