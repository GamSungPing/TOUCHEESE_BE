package com.rocket.toucheese_be.domain.studio.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDetailDto(
        @JsonIgnore Long id,
        String name,
        int price,
        boolean isGroup,
        int basePeopleCnt,
        int addPeoplePrice,
        List<String> productOptions
) {
    public ProductDetailDto(Product product) {
        this(
                product.getId(),
                product.getProductName(),
                product.getProductPrice(),
                product.isGroupFlag(),
                product.getBasePeopleCnt(),
                product.getAddPeoplePrice(),
                List.of(product.getProductOptions().split("&"))
        );
    }

    public ProductDetailDto() {
        this(
                null,
                null,
                -1,
                false,
                -1,
                -1,
                null
        );
    }
}