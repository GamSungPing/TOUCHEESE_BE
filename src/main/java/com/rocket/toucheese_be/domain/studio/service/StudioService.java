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

@Service
@RequiredArgsConstructor
@Transactional
public class StudioService {
    private final StudioRepository studioRepository;

    // 스튜디오 단일 조회
    public StudioDto getStudio(Long id) {
        Studio studio = studioRepository.findById(id).orElse(null);
        if(studio == null) return null;
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
    public Page<Studio> getStudiosByConceptAndRegion(Long conceptId, List<Long> regionIds, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIds(conceptId, regionIds, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별 + 가격+낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudioByConceptWithLowPrice(Long conceptId, String priceCategory, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdOrderByProfilePriceAsc(conceptId, priceCategory, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별, 평점 높은 순으로 스튜디오 정렬
    public Page<Studio> getStudiosByConceptAndRegionAndRating(Long conceptId, List<Long> regionIds, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIdsOrderByAverageRatingDesc(conceptId, regionIds, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별, 가격+낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudiosByConceptAndRegionAndLowPrice(Long conceptId, List<Long> regionIds, String priceCategory, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIdsOrderByProfilePriceAsc(conceptId, regionIds, priceCategory, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 평점 높은 순, 가격+낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudioByConceptOrderByHighRatingAndLowPrice(Long conceptId, String priceCategory, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdOrderByAverageRatingDescAndProfilePriceAsc(conceptId, priceCategory, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉별, 지역별, 평점 높은 순, 가격+낮은 순으로 스튜디오 정렬
    public Page<Studio> getStudioByConceptAndRegionOrderByHighRatingAndLowPrice(Long conceptId, List<Long> regionIds, String priceCategory, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptIdAndRegionIdsOrderByAverageRatingDescAndProfilePriceAsc(conceptId, regionIds, priceCategory, pageable);
        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }
}
