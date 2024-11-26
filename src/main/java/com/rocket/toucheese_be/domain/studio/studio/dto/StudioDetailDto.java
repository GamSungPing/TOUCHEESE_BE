package com.rocket.toucheese_be.domain.studio.studio.dto;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;

import java.util.List;

public record StudioDetailDto(
        Long studioId,
        String name,
        Double rating,
        List<ProductDto> products
) {
    public StudioDetailDto(Studio studio, List<Product> products) {
        this(
                studio.getId(),
                studio.getName(),
                studio.getRating(),
                products.stream()
                        .map(ProductDto::new)
                        .toList()
        );
    }

}

/*
*
struct StudioDetail {
    let detailImageStrings: [String]

    let reviewCount: Int
    let businessHours: String
    let address: String

    let notice: String?
    let products: [Product]
    var reviews: [Review]?
}
* */