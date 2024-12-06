package com.rocket.toucheese_be.domain.studio.product.dto;

import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import lombok.Builder;

@Builder
public record ProductDto (
        Long id,
        String name,
        String description,
        String imageString,
        int reviewCnt,
        int price,
        boolean isGroup
) {
    public ProductDto(Product product, int reviewCnt) {
        this(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getProductImage(),
                reviewCnt,
                product.getProductPrice(),
                product.isGroupFlag()
        );
    }
}