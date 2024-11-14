package com.rocket.toucheese_be.domain.studio.controller;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.service.StudioService;
import com.rocket.toucheese_be.global.response.Response;
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
    public Response<List<Studio>> getAllStudios() {
        List<Studio> studioList = studioService.getAllStudios();
        return Response.of("all studios", studioList);
    }

    @GetMapping("/{id}")
    public Response<Studio> getStudio(@PathVariable("id") Long id) {
        Studio studio = studioService.getStudio(id);
        return Response.of("id studio", studio);
    }

    @GetMapping("/concept/{conceptId}")
    public Response<List<Studio>> getStudioByConcept(@PathVariable("conceptId") Long conceptId) {
        List<Studio> studioList = studioService.getStudioByConcept(conceptId);
        return Response.of(conceptId+"'s studios", studioList);
    }
}
