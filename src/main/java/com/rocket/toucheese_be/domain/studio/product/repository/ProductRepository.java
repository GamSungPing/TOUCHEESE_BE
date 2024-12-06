package com.rocket.toucheese_be.domain.studio.product.repository;

import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 스튜디오 아이디로 상품 리스트 조회
    List<Product> findProductsByStudioId(@Param("studioId") Long studioId);

    // 아이디로 상품 단건 조회
    Optional<Product> findById(@Param("id") Long id);

    // 상품에 있는 리뷰 개수 조회
    @Query("SELECT COUNT(r) FROM Review r WHERE r.product.id = :productId")
    int countReviewsByProductId(@Param("productId") Long productId);
}
