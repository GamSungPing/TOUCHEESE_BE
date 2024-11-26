package com.rocket.toucheese_be.domain.studio.studio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PriceCategory {
    LOW(0, 99_999, "LOW"),
    MEDIUM(100_000, 199_999, "MEDIUM"),
    HIGH(200_000, Integer.MAX_VALUE, "HIGH");

    private final int minPrice;
    private final int maxPrice;
    private final String priceName;
}