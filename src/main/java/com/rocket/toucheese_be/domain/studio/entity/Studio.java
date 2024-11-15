package com.rocket.toucheese_be.domain.studio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    private List<StudioConcept> studioConceptList;

    @JsonIgnore
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    private List<Rating> ratingList;

    @Setter
    @Transient // 데이터베이스에 저장하지 않음
    private Double averageRating;

    public Double calculateAverageRating() {
        if (ratingList == null || ratingList.isEmpty()) {
            return 0.0;
        }
        double sum = ratingList.stream()
                .mapToInt(Rating::getRating)
                .sum();
        return Math.round((sum / ratingList.size()) * 10) / 10.0; // 소수점 첫째 자리까지 반올림
    }
}
