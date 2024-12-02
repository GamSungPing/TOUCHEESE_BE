package com.rocket.toucheese_be.domain.studio.studio.service;

import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import com.rocket.toucheese_be.domain.studio.studio.repository.StudioRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudioService {
    private final StudioRepository studioRepository;

    // 스튜디오 단일 조회
    public Studio getStudio(Long id) {
        Studio studio = studioRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STUDIO));
        studio.setRating(studio.calculateAverageRating());
        return studio;
    }

    // 특정 컨셉에 해당하는 스튜디오 리스트 조회
    public Page<Studio> getStudioByConcept(Long conceptId, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByConceptId(conceptId, pageable);

        if (!studios.hasContent()) {
            throw new CustomException(ErrorCode.NOT_FOUND_CONCEPT_STUDIO);
        }

        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

    // 컨셉+지역+가격+평점 조합 필터링 및 정렬 (QueryDSL 적용 동적 쿼리 생성)
    public Page<Studio> getStudiosByFilters(Long conceptId, List<Long> regionIds, String priceCategory, boolean sortRating, Pageable pageable) {
        Page<Studio> studios = studioRepository.findStudiosByFilters(conceptId, regionIds, priceCategory, sortRating, pageable);

        if (!studios.hasContent()) {
            throw new CustomException(ErrorCode.NOT_FOUND_STUDIO_PRODUCT);
        }

        studios.forEach(studio -> studio.setRating(studio.calculateAverageRating()));
        return studios;
    }

}