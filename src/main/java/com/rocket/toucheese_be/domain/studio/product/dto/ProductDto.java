package com.rocket.toucheese_be.domain.studio.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import lombok.Builder;

@Builder
public record ProductDto (
        @JsonIgnore Long id
) {
    public ProductDto(Product product) {
        this(product.getId());
    }
}

/*
*
struct ProductDetail {
    let isGroup: Bool
    let baseGuestCount: Int?
    let addPeoplePrice: Int?

    let productOptions: [ProductOption]
}
struct ProductOption {
    let id: String
    let name: String
    let price: Int
}
**/