package com.rocket.toucheese_be.domain.studio.studio.service;

import com.rocket.toucheese_be.domain.studio.studio.entity.Concept;
import com.rocket.toucheese_be.domain.studio.studio.repository.ConceptRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotStudioService {
    private final ConceptRepository conceptRepository;

    // 모든 컨셉 조회
    public List<Concept> getAllConcept() {
        List<Concept> concepts = conceptRepository.findAll();
        if (concepts.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_EXCEPTION,
                    "컨셉 목록이 존재하지 않습니다.");
        }
        return concepts;
    }

    // 특정 ID의 컨셉 조회
    public Concept getConceptById(Long id) {
        return conceptRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_EXCEPTION,
                        "ID " + id + "에 해당하는 컨셉이 존재하지 않습니다."));
    }
}