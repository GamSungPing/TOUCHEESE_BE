package com.rocket.toucheese_be.domain.studio.service;

import com.rocket.toucheese_be.domain.studio.dto.StudioDto;
import com.rocket.toucheese_be.domain.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Studio> getStudioByConcept(Long conceptId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptId(conceptId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉 별로 평균 평점이 높은 순으로 스튜디오 정렬
    public Page<Studio> getStudioByConceptWithHighRating(Long conceptId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdOrderByAverageRatingDesc(conceptId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별 필터링 된 스튜디오 리스트 조회
    public Page<Studio> getStudiosByConceptAndRegion(Long conceptId, Long regionId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionId(conceptId, regionId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별 + 프로필 가격이 낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudioByConceptWithLowPrice(Long conceptId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdOrderByProfilePriceAsc(conceptId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별, 평점 높은 순으로 스튜디오 정렬
    public Page<Studio> getStudiosByConceptAndRegionAndRating(Long conceptId, Long regionId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIdOrderByAverageRatingDesc(conceptId, regionId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별, 가격 낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudiosByConceptAndRegionAndLowPrice(Long conceptId, Long regionId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIdOrderByProfilePriceAsc(conceptId, regionId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 평점 높은 순 -> 프로필 가격이 낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudioByConceptOrderByHighRatingAndLowPrice(Long conceptId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdOrderByAverageRatingDescAndProfilePriceAsc(conceptId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    public Page<Studio> getStudioByConceptAndRegionOrderByHighRatingAndLowPrice(Long conceptId, Long regionId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIdOrderByAverageRatingDescAndProfilePriceAsc(conceptId, regionId, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }
}
