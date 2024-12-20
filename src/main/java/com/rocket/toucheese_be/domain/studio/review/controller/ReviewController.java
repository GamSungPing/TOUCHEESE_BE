package com.rocket.toucheese_be.domain.studio.review.controller;

import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDetailDto;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.review.service.ReviewService;
import com.rocket.toucheese_be.global.config.AppConfig;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import com.rocket.toucheese_be.standard.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/review", produces = APPLICATION_JSON_VALUE)
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "스튜디오 리뷰 목록 조회",
            description = "특정 스튜디오의 리뷰 목록을 페이징 처리하여 조회합니다. 리뷰 목록에는 각 리뷰의 대표 사진(첫 번째 사진)이 포함됩니다."
    )
    @GetMapping("/studio/{studioId}")
    public Response<PageDto<ReviewDto>> getReviewListByStudioId(
            @PathVariable("studioId") Long studioId,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getReviewPageSize(), Sort.by("id").ascending());
        Page<ReviewDto> reviewPage = reviewService.getReviewsWithFirstPhotoByStudioId(studioId, pageable);

        return Response.of(SuccessCode.GET_STUDIO_TOTAL_REVIEW_PHOTO_LIST, new PageDto<>(reviewPage));
    }

    @Operation(
            summary = "특정 리뷰 상세 조회",
            description = "특정 스튜디오 내에서 리뷰 ID를 사용하여 리뷰의 상세 정보를 조회합니다. 리뷰의 작성자, 작성일, 내용, 평점, 첨부 이미지 등을 확인할 수 있습니다."
    )
    @GetMapping("/studio/{studioId}/detail/{reviewId}")
    public Response<ReviewDetailDto> getReviewDetailByStudioIdAndReviewId(
            @PathVariable("studioId") Long studioId,
            @PathVariable("reviewId") Long reviewId
    ) {
        ReviewDetailDto reviewDetailDto = reviewService.getReviewDetailByStudioAndReview(studioId, reviewId);
        return Response.of(SuccessCode.GET_REVIEW_DETAIL, reviewDetailDto);
    }

    @Operation(
            summary = "특정 상품 리뷰 목록 조회",
            description = "특정 스튜디오의 특정 상품에 대한 리뷰 목록을 페이징 처리하여 조회합니다. 각 리뷰에는 대표 사진(첫 번째 사진)이 포함됩니다."
    )
    @GetMapping("/studio/{studioId}/product/{productId}")
    public Response<PageDto<ReviewDto>> getReviewListByStudioIdAndProductId(
            @PathVariable("studioId") Long studioId,
            @PathVariable("productId") Long productId,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getReviewPageSize(), Sort.by("id").ascending());
        Page<ReviewDto> reviewPage = reviewService.getReviewsByStudioIdAndProductId(studioId, pageable, productId);

        return Response.of(SuccessCode.GET_PRODUCT_REVIEW_LIST, new PageDto<>(reviewPage));
    }
}
