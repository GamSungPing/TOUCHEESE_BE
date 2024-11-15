package com.rocket.toucheese_be.domain.studio.service;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudioService {
    private final StudioRepository studioRepository;

    // 모든 스튜디오와 평균 평점을 가져오는 메서드
    public List<Studio> getAllStudios() {
        List<Studio> studios = studioRepository.findAll();
        studios.forEach(studio -> studio.setAverageRating(studio.calculateAverageRating()));
        return studios;
    }

    // 스튜디오 ID로 스튜디오와 평균 평점을 가져오는 메서드
    public Studio getStudio(Long id) {
        Studio studio = studioRepository.findStudioById(id)
                .orElseThrow(() -> new RuntimeException("그런 스튜디오 없음"));
        studio.setAverageRating(studio.calculateAverageRating());
        return studio;
    }

    // 특정 컨셉에 해당하는 스튜디오 리스트 조회
    public List<Studio> getStudioByConcept(Long conceptId) {
        List<Studio> studios = studioRepository.findStudiosByConceptId(conceptId);
        studios.forEach(studio -> studio.setAverageRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별로 평균 평점이 높은 순으로 스튜디오 정렬
    public List<Studio> getStudioByConceptWithHighRating(Long conceptId) {
        List<Studio> studios = studioRepository.findStudiosByConceptIdOrderByAverageRatingDesc(conceptId);
        studios.forEach(studio -> studio.setAverageRating(studio.calculateAverageRating()));
        return studios;
    }
}
