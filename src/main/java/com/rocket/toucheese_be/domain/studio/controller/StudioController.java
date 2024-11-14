package com.rocket.toucheese_be.domain.studio.controller;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.service.StudioService;
import com.rocket.toucheese_be.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api/v1/studio", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class StudioController {
    private final StudioService studioService;

    @GetMapping("/")
    public RsData<List<Studio>> getAllStudios() {
        List<Studio> studioList = studioService.getAllStudios();
        return RsData.of("all studios", studioList);
    }

    @GetMapping("/{id}")
    public RsData<Studio> getStudio(@PathVariable("id") Long id) {
        Studio studio = studioService.getStudio(id);
        return RsData.of("id studio", studio);
    }

    @GetMapping("/concept/{conceptId}")
    public RsData<List<Studio>> getStudioByConcept(@PathVariable("conceptId") Long conceptId) {
        List<Studio> studioList = studioService.getStudioByConcept(conceptId);
        return RsData.of(conceptId+"'s studios", studioList);
    }
}
