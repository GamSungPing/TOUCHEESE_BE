package com.rocket.toucheese_be.domain.studio.controller;

import com.rocket.toucheese_be.domain.studio.dto.ConceptListDto;
import com.rocket.toucheese_be.domain.studio.entity.Concept;
import com.rocket.toucheese_be.domain.studio.service.NotStudioService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class NotStudioController {

    private final NotStudioService service;

    @GetMapping("concepts")
    public Response<List<ConceptListDto>> getAllConcept() {
        List<Concept> conceptList = service.getAllConcept();
        List<ConceptListDto> conceptDtoList = ConceptListDto.fromConceptList(conceptList);
        return Response.of(SuccessCode.GET_CONCEPT_LIST_SUCCESS, conceptDtoList);
    }

    @GetMapping("concept/{id}")
    public Response<ConceptListDto> getConcept(@PathVariable("id") Long id) {
        Concept concept = service.getConceptById(id);
        ConceptListDto conceptDtoList = new ConceptListDto(concept);
        return Response.of(SuccessCode.GET_CONCEPT_ONE_SUCCESS, conceptDtoList);
    }
}