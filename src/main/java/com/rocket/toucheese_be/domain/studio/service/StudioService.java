package com.rocket.toucheese_be.domain.studio.service;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudioService {
    private final StudioRepository studioRepository;

    public Page<Studio> getAllStudios(Pageable pageable) {
        return studioRepository.findAll(pageable);
    }

    public Studio getStudio(Long id) {
        return studioRepository.findStudioById(id)
                                .orElseThrow(() -> new RuntimeException("그런 스튜디오 없음"));
    }

    public List<Studio> getStudioByConcept(Long conceptId) {
        return studioRepository.findStudiosByConceptId(conceptId);
    }
}
