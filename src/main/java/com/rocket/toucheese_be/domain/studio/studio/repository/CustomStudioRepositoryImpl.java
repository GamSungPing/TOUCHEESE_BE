package com.rocket.toucheese_be.domain.studio.studio.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.rocket.toucheese_be.domain.studio.review.entity.QReview;
import com.rocket.toucheese_be.domain.studio.studio.entity.*;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
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

    public Page<Studio> findStudiosByFilters(Long conceptId, List<Long> regionIds, String priceCategory, boolean sortRating, Pageable pageable) {
        QStudio studio = QStudio.studio;
        QStudioConcept concept = QStudioConcept.studioConcept;
        QRegion region = QRegion.region;
        QReview review = QReview.review;

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
                .join(studio.studioConceptList, concept)
                .leftJoin(studio.region, region)
                .leftJoin(studio.reviewList, review)
                .where(builder)
                .groupBy(studio.id, studio.name);

        // 조건부 정렬
        if (sortRating) {
            query.orderBy(review.rating.avg().desc(), studio.profilePrice.asc());
        } else {
            query.orderBy(studio.profilePrice.asc());
        }

        // Count Query 별도 생성
        JPAQuery<Long> countQuery = new JPAQuery<>(entityManager);
        Long total = countQuery.select(studio.countDistinct())
                .from(studio)
                .join(studio.studioConceptList, concept)
                .leftJoin(studio.region, region)
                .leftJoin(studio.reviewList, review)
                .where(builder)
                .fetchOne();
        if(total == null) total = 0L; // null 일리가 없겠지만...

        // 페이징 처리
        List<Studio> result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Page 반환
        return new PageImpl<>(result, pageable, total);
    }
}