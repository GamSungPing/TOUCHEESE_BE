package com.rocket.toucheese_be.domain.studio.controller;

import com.rocket.toucheese_be.domain.studio.dto.StudioListDto;
import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.service.StudioService;
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

@RequestMapping(value = "/api/v1/studio", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class StudioController {
    private final StudioService studioService;

    // 모든 스튜디오 리스트 조회 with 평점 - Page 적용 완료
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

    // 특정 스튜디오 조회 with 평점 - Page 적용 완료
    @GetMapping("/{id}")
    public Response<Studio> getStudio(@PathVariable("id") Long id) {
        Studio studio = studioService.getStudio(id);
        return Response.of(SuccessCode.GET_STUDIO_ONE_SUCCESS, studio);
    }

    // 특정 컨셉에 해당하는 스튜디오 리스트 조회 with 평점 - Page 적용 완료
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

    // 컨셉 필터링 + 인기순 정렬 (평점) - Page 적용 완료
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

    // studio -> studioListDto
    private StudioListDto studioToDto(Studio studio) {
        return new StudioListDto(studio);
    }
}
