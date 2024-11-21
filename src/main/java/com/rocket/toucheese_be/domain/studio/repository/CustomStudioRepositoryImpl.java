package com.rocket.toucheese_be.domain.studio.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.rocket.toucheese_be.domain.studio.entity.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class CustomStudioRepositoryImpl implements CustomStudioRepository {
    private final EntityManager entityManager;

    public Page<Studio> findStudiosByFilters(Long conceptId, List<Long> regionIds, String priceCategory, Pageable pageable) {
        QStudio studio = QStudio.studio;
        QStudioConcept concept = QStudioConcept.studioConcept;
        QRegion region = QRegion.region;

        // 조건 빌더 생성
        BooleanBuilder builder = new BooleanBuilder();

        // 컨셉 ID 조건
        if (conceptId != null) {
            builder.and(concept.concept.id.eq(conceptId));
        }

        // 지역 ID 조건
        if (regionIds != null && !regionIds.isEmpty()) {
            builder.and(region.id.in(regionIds));
        }

        // 가격 카테고리 조건
        if (priceCategory != null) {
            builder.and(studio.priceCategory.eq(priceCategory));
        }

        // Query 생성
        JPAQuery<Studio> query = new JPAQuery<>(entityManager);
        query.select(studio)
                .from(studio)
                .leftJoin(studio.studioConceptList, concept)
                .leftJoin(studio.region, region)
                .where(builder)
                .groupBy(studio.id, studio.name)
                .orderBy(studio.profilePrice.asc(), studio.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        // 결과 페칭
        List<Studio> result = query.fetch();
        long total = query.fetchCount();

        // Page 반환
        return new PageImpl<>(result, pageable, total);
    }
}
