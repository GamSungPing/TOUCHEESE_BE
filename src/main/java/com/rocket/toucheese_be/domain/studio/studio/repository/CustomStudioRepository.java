package com.rocket.toucheese_be.domain.studio.studio.repository;

import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomStudioRepository {
    Page<Studio> findStudiosByFilters(Long conceptId, List<Long> regionIds, String priceCategory, boolean sortRating, Pageable pageable);
}