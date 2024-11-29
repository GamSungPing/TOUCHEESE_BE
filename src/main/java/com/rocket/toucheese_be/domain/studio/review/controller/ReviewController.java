package com.rocket.toucheese_be.domain.studio.review.controller;

import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDetailDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewPhotoDto;
import com.rocket.toucheese_be.domain.studio.review.service.ReviewService;
import com.rocket.toucheese_be.global.app.AppConfig;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import com.rocket.toucheese_be.standard.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/review", produces = APPLICATION_JSON_VALUE)
public class ReviewController {

    private final ReviewService reviewService;

    // 스튜디오 ID로 리뷰 이미지 목록 조회 API (페이징 포함)
    @GetMapping("/studio/{studioId}/photos")
    public Response<PageDto<ReviewPhotoDto>> getReviewImagesByStudioId(
            @PathVariable Long studioId,
            @RequestParam(defaultValue = "1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by("id").ascending());
        Page<ReviewPhotoDto> reviewPhotoPage = reviewService.getReviewImagesByStudioId(studioId, pageable);

        return Response.of(SuccessCode.GET_STUDIO_TOTAL_REVIEW_PHOTO_LIST, new PageDto<>(reviewPhotoPage));
    }


    // 특정 스튜디오 안에서 특정 리뷰 상세 조회 API
    @GetMapping("/studio/{studioId}/detail/{reviewId}")
    public Response<ReviewDetailDto> getReviewDetailByStudioIdAndReviewId(
            @PathVariable Long studioId,
            @PathVariable Long reviewId
    ) {
        ReviewDetailDto reviewDetailDto = reviewService.getReviewDetailByStudioAndReview(studioId, reviewId);
        return Response.of(SuccessCode.GET_REVIEW_DETAIL, reviewDetailDto);
    }
}
