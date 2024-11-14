package com.rocket.toucheese_be.domain.studio.service;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudioService {
    private final StudioRepository studioRepository;

    public List<Studio> getAllStudios() {
        return studioRepository.findAll();
    }

    public Studio getStudio(Long id) {
        return studioRepository.findStudioById(id)
                                .orElseThrow(() -> new RuntimeException("그런 스튜디오 없음"));
    }
}
