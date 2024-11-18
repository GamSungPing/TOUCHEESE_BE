package com.rocket.toucheese_be.domain.studio.controller;

import com.rocket.toucheese_be.domain.studio.dto.StudioDto;
import com.rocket.toucheese_be.domain.studio.dto.StudioListDto;
import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.service.StudioService;
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

    // studio -> studioListDto
    private StudioListDto studioToDto(Studio studio) {
        return new StudioListDto(studio);
    }

    @Operation(summary = "모든 스튜디오 리스트 조회",
            description = "모든 스튜디오 리스트를 평점과 함께 조회합니다. 페이지네이션 기능이 적용되어 있으며, 기본적으로 1페이지부터 조회됩니다.",
            tags = {"Studio"})
    @GetMapping("/")
    public Response<PageDto<StudioListDto>> getAllStudios(
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));

        Page<Studio> studioPage = studioService.getAllStudios(pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "특정 스튜디오 조회",
            description = "특정 스튜디오의 상세 정보를 조회합니다. 해당 스튜디오는 평점과 함께 제공됩니다.",
            tags = {"Studio"})
    @GetMapping("/{id}")
    public Response<StudioDto> getStudio(@PathVariable("id") Long id) {
        StudioDto studioDto = studioService.getStudio(id);
        return Response.of(SuccessCode.GET_STUDIO_ONE_SUCCESS, studioDto);
    }

    @Operation(summary = "특정 컨셉에 해당하는 스튜디오 리스트 조회",
            description = "특정 컨셉에 해당하는 스튜디오 리스트를 평점과 함께 조회합니다. 페이지네이션 기능이 적용되어 있습니다.",
            tags = {"Studio"})
    @GetMapping("/concept/{conceptId}")
    public Response<PageDto<StudioListDto>> getStudioByConcept(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize(), Sort.by(sorts));

        Page<Studio> studioPage = studioService.getStudioByConcept(conceptId, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_BY_CONCEPT_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉 필터링 + 인기순 정렬 (평점) 조회",
            description = "특정 컨셉에 해당하는 스튜디오 리스트를 평점 순으로 정렬하여 조회합니다. 페이지네이션 기능이 적용되어 있습니다.",
            tags = {"Studio"})
    @GetMapping("/concept/{conceptId}/high-rating")
    public Response<PageDto<StudioListDto>> getStudioByConceptWithHighRating(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudioByConceptWithHighRating(conceptId, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_RATING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉 필터링 + 지역 필터링 조회",
            description = "특정 컨셉과 지역에 해당하는 스튜디오 리스트를 조회합니다. 페이지네이션 기능이 적용되어 있으며, 기본적으로 1페이지부터 조회됩니다.",
            tags = {"Studio"})
    @GetMapping("/concept/{conceptId}/region/{regionId}")
    public Response<PageDto<StudioListDto>> getStudiosByConceptAndRegion(
            @PathVariable("conceptId") Long conceptId,
            @PathVariable("regionId") Long regionId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudiosByConceptAndRegion(conceptId, regionId, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_BY_CONCEPT_AND_REGION_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉 필터링 + 가격순 정렬 (프로필 사진 기준) 조회",
            description = "특정 컨셉에 해당하는 스튜디오 리스트를 가격순으로 정렬하여 조회합니다. 페이지네이션 기능이 적용되어 있습니다.",
            tags = {"Studio"})
    @GetMapping("/concept/{conceptId}/low-pricing")
    public Response<PageDto<StudioListDto>> getStudioByConceptWithLowPrice(
            @PathVariable("conceptId") Long conceptId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudioByConceptWithLowPrice(conceptId, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_PRICING_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @Operation(summary = "컨셉 + 지역 + 인기순 정렬 + 가격순 정렬 조회",
            description = "특정 컨셉, 지역, 인기순 정렬, 가격순 정렬을 결합한 스튜디오 리스트를 조회합니다. 페이지네이션 기능이 적용되어 있습니다.",
            tags = {"Studio"})
    @GetMapping("/concept/{conceptId}/region/{regionId}/high-rating/low-pricing")
    public Response<PageDto<StudioListDto>> getStudioByConceptAndRegionWithHighRatingAndLowPrice(
            @PathVariable("conceptId") Long conceptId,
            @PathVariable("regionId") Long regionId,
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize());

        Page<Studio> studioPage = studioService.getStudioByConceptAndRegionOrderByHighRatingAndLowPrice(conceptId, regionId, pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_REGION_RATING_PRICING_SUCCESS, new PageDto<>(studioListDtoPage));
    }
}