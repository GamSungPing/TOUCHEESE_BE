package com.rocket.toucheese_be.domain.studio.studio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(callSuper = false)
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Long id;

    private String name;

    private int profilePrice;

    private String priceCategory;

    @OneToMany(mappedBy = "studio")
    private List<StudioConcept> studioConceptList;

    @Builder.Default
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratingList = new ArrayList<>();

    @Setter
    @Transient // 데이터베이스에 저장하지 않음
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder.Default
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToOne(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profileImage;


    public Double calculateAverageRating() {
        if (ratingList == null || ratingList.isEmpty()) {
            return 0.0;
        }
        double sum = ratingList.stream()
                .mapToInt(Rating::getRating)
                .sum();
        return Math.round((sum / ratingList.size()) * 10) / 10.0; // 소수점 첫째 자리까지 반올림
    }

    @PrePersist
    @PreUpdate
    public void calculatePriceCategory() {
        this.priceCategory = calculatePriceCategoryBasedOnProfilePrice(profilePrice);
    }

    private String calculatePriceCategoryBasedOnProfilePrice(int price) {
        if(price <= PriceCategory.LOW.getMaxPrice()) {
            return PriceCategory.LOW.getPriceName();
        } else if(price <= PriceCategory.MEDIUM.getMaxPrice()) {
            return PriceCategory.MEDIUM.getPriceName();
        } else {
            return PriceCategory.HIGH.getPriceName();
        }
    }

}
