package com.rocket.toucheese_be.domain.studio.service;

import com.rocket.toucheese_be.domain.studio.dto.StudioDto;
import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudioService {
    private final StudioRepository studioRepository;
    
    // 모든 스튜디오와 평점 가져오기
    public Page<Studio> getAllStudios(Pageable pageable) {
        Page<Studio> studios = studioRepository.findAll(pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }


    // 스튜디오 단일 조회
    public StudioDto getStudio(Long id) {

        Studio studio = studioRepository.findById(id).orElse(null);
        studio.setRating(studio.calculateAverageRating());

        return StudioDto.fromEntity(studio);
    }



    // 특정 컨셉에 해당하는 스튜디오 리스트 조회
    public List<StudioDto> getStudioByConcept(Long conceptId) {
        List<Studio> studios = studioRepository.findStudiosByConceptId(conceptId); // Studio 엔티티 리스트 조회
        return studios.stream()
                .map(studio -> StudioDto.fromEntity(studio)) // StudioDto로 변환
                .collect(Collectors.toList());
    }

    // 컨셉별로 평균 평점이 높은 순으로 스튜디오 정렬
    public List<Studio> getStudioByConceptWithHighRating(Long conceptId) {
        List<Studio> studios = studioRepository.findStudiosByConceptIdOrderByAverageRatingDesc(conceptId);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별 필터링된 스튜디오 리스트 조회
    public List<Studio> getStudiosByConceptAndRegion(Long conceptId, Long regionId) {
        List<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionId(conceptId, regionId);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

}
