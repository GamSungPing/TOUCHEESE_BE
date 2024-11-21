package com.rocket.toucheese_be.domain.studio.repository;

import com.rocket.toucheese_be.domain.studio.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomStudioRepository {
    public Page<Studio> findStudiosByFilters(Long conceptId, List<Long> regionIds, String priceCategory, Pageable pageable);
}
