package com.rocket.toucheese_be.domain.concept.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
@Getter
public class ConceptDto {
    private Long id;
    private String name;
}
