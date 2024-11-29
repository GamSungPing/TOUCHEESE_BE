package com.rocket.toucheese_be.domain.studio.studio.controller;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.product.service.ProductService;
import com.rocket.toucheese_be.domain.studio.review.dto.ReviewDto;
import com.rocket.toucheese_be.domain.studio.review.service.ReviewService;
import com.rocket.toucheese_be.domain.studio.studio.dto.StudioDetailDto;
import com.rocket.toucheese_be.domain.studio.studio.dto.StudioListDto;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.service.StudioService;
import com.rocket.toucheese_be.global.app.AppConfig;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api/v1/studio", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class StudioController {
    private final StudioService studioService;
    private final ProductService productService;
    private final ReviewService reviewService;

    // studio -> studioListDto
    private StudioListDto studioToDto(Studio studio) {
        return new StudioListDto(studio);
    }

    @Operation(summary = "특정 스튜디오 조회", description = "스튜디오 ID를 통해 특정 스튜디오의 상세 정보를 평점과 함께 조회합니다.")
    @GetMapping("/{id}")
    public Response<StudioListDto> getStudio(@PathVariable("id") Long id) {
        Studio studio = studioService.getStudio(id);
        if(studio == null) return Response.of(SuccessCode.GET_STUDIO_ONE_SUCCESS, new StudioListDto());

        StudioListDto studioListDto = new StudioListDto(studio);
        return Response.of(SuccessCode.GET_STUDIO_ONE_SUCCESS, studioListDto);
    }

    @Operation(summary = "특정 스튜디오 상세 조회", description = "스튜디오 ID를 통해 특정 스튜디오의 상세 정보를 평점과 함께 조회합니다.")
    @GetMapping("/detail/{id}")
    public Response<StudioDetailDto> getStudioDetail(
            @PathVariable("id") Long id,
        @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getReviewPageSize(), Sort.by("id").ascending());

        Studio studio = studioService.getStudio(id);
        List<ProductDto> productListDto = productService.getProductListByStudioId(id);
        Page<ReviewDto> reviewPhotoDtoPage = reviewService.getReviewsWithFirstPhotoByStudioId(id, pageable);
        StudioDetailDto studioDetailDto = new StudioDetailDto(studio, productListDto, new PageDto<>(reviewPhotoDtoPage));
        return Response.of(SuccessCode.GET_STUDIO_DETAiL_SUCCESS, studioDetailDto);
    }

    @Operation(summary = "특정 컨셉의 스튜디오 조회", description = "컨셉 ID에 해당하는 스튜디오 리스트를 평점과 함께 조회합니다.")
    @GetMapping("/concept/{conceptId}")
    public Response<PageDto<StudioListDto>> getStudioByConcept(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));

        Page<Studio> studioPage = studioService.getStudioByConcept(conceptId, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_BY_CONCEPT_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "특정 컨셉의 스튜디오 조회 (인기순)", description = "컨셉 ID에 해당하는 스튜디오를 평점 순으로 정렬하여 조회합니다.")
    @GetMapping("/concept/{conceptId}/high-rating")
    public Response<PageDto<StudioListDto>> getStudioByConceptWithHighRating(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, null, null, true, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_RATING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "특정 컨셉 및 지역의 스튜디오 조회 QueryDSL", description = "컨셉 ID와 지역 ID에 해당하는 스튜디오 리스트를 조회합니다.")
    @GetMapping("/concept/{conceptId}/regions")
    public Response<PageDto<StudioListDto>> getStudiosByConceptAndRegion(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name = "regionIds") List<Long> regionIds,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, regionIds, null, false, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_BY_CONCEPT_AND_REGION_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "특정 컨셉의 스튜디오 조회 (가격순) QueryDSL", description = "컨셉 ID에 해당하는 스튜디오를 가격 순으로 정렬하여 조회합니다.")
    @GetMapping("/concept/{conceptId}/low-pricing")
    public Response<PageDto<StudioListDto>> getStudioByConceptWithLowPrice(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name = "priceCategory", defaultValue = "LOW") String priceCategory,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, null, priceCategory, false, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_PRICING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉과 지역을 기준으로 인기 내림차순으로 정렬된 스튜디오 리스트 조회", description = "컨셉과 지역을 필터링하여 인기 내림차순으로 정렬된 스튜디오 리스트를 조회합니다.")
    @GetMapping("/concept/{conceptId}/high-rating/regions")
    public Response<PageDto<StudioListDto>> getStudiosByConceptAndRegionAndRating(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="regionIds") List<Long> regionIds,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, regionIds, null, true, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_REGION_RATING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉과 지역을 기준으로 가격 오름차순으로 정렬된 스튜디오 리스트 조회 QueryDSL", description = "컨셉과 지역을 필터링하여 가격 오름차순으로 정렬된 스튜디오 리스트를 조회합니다.")
    @GetMapping("/concept/{conceptId}/regions/low-pricing")
    public Response<PageDto<StudioListDto>> getStudiosByConceptAndRegionAndLowPrice(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="regionIds", required=false) List<Long> regionIds,
            @RequestParam(name = "priceCategory", required=false) String priceCategory,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, regionIds, priceCategory, false, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_REGION_PRICING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉을 기준으로 인기 내림차순 및 가격 오름차순으로 정렬된 스튜디오 리스트 조회", description = "컨셉을 기준으로 인기 내림차순 및 가격 오름차순으로 정렬된 스튜디오 리스트를 조회합니다.")
    @GetMapping("/concept/{conceptId}/high-rating/low-pricing")
    public Response<PageDto<StudioListDto>> getStudioByConceptWithHighRatingAndLowPrice(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name = "priceCategory", defaultValue = "LOW") String priceCategory,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, null, priceCategory, true, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_RATING_PRICING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉과 지역을 기준으로 인기 내림차순 및 가격 오름차순으로 정렬된 스튜디오 리스트 조회", description = "컨셉과 지역을 필터링하여 인기 내림차순 및 가격 오름차순으로 정렬된 스튜디오 리스트를 조회합니다.")
    @GetMapping("/concept/{conceptId}/high-rating/regions/low-pricing")
    public Response<PageDto<StudioListDto>> getStudioByConceptAndRegionWithHighRatingAndLowPrice(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="regionIds") List<Long> regionIds,
            @RequestParam(name = "priceCategory", defaultValue = "LOW") String priceCategory,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByFilters(conceptId, regionIds, priceCategory, true, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_REGION_RATING_PRICING_SUCCESS, new PageDto<>(studioListDtoPage));
    }
}