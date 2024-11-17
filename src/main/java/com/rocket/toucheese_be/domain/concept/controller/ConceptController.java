package com.rocket.toucheese_be.domain.concept.controller;

import com.rocket.toucheese_be.domain.concept.entity.Concept;
import com.rocket.toucheese_be.domain.concept.service.ConceptService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api/v1/concept", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class ConceptController {
    private final ConceptService conceptService;

    @GetMapping("/")
    public Response<List<Concept>> getAllConcepts() {
        List<Concept> conceptList = conceptService.getAllConcepts();
        return Response.of(SuccessCode.GET_CONCEPT_LIST_SUCCESS, conceptList);
    }

    @GetMapping("/{id}")
    public Response<Concept> getConcept(@PathVariable("id") Long id) {
        Concept concept = conceptService.getConcept(id);
        return Response.of(SuccessCode.GET_STUDIO_ONE_SUCCESS, concept);
    }
}
