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

    // Page 적용 시도
    private StudioListDto studioToDto(Studio studio) {
        return new StudioListDto(studio);
    }
    @GetMapping("/")
    public Response<PageDto<StudioListDto>> getAllStudios(
            @RequestParam(name="page", defaultValue="1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, AppConfig.getBasePageSize() - 1, Sort.by(sorts));

        Page<Studio> studioPage = studioService.getAllStudios(pageable);
        Page<StudioListDto> studioListDtoPage = studioPage.map(this::studioToDto);
        return Response.of(SuccessCode.GET_STUDIO_LIST_SUCCESS, new PageDto<>(studioListDtoPage));
    }

    @GetMapping("/{id}")
    public Response<Studio> getStudio(@PathVariable("id") Long id) {
        Studio studio = studioService.getStudio(id);
        return Response.of(SuccessCode.GET_STUDIO_ONE_SUCCESS, studio);
    }

    @GetMapping("/concept/{conceptId}")
    public Response<List<Studio>> getStudioByConcept(@PathVariable("conceptId") Long conceptId) {
        List<Studio> studioList = studioService.getStudioByConcept(conceptId);
        return Response.of(SuccessCode.GET_STUDIO_LIST_WITH_CONCEPT_SUCCESS, studioList);
    }
}
