package com.rocket.toucheese_be.domain.studio.studio.entity;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

    private String photographer;

    private int profilePrice;

    private String priceCategory;

    @OneToMany(mappedBy = "studio")
    private List<StudioConcept> studioConceptList;

    @Builder.Default
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @Setter
    @Transient // 데이터베이스에 저장하지 않음
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private String address;

    private String notice;

    @Column
    private LocalTime openingTime = LocalTime.of(10, 0); // 영업 시작 시간

    @Column
    private LocalTime closingTime = LocalTime.of(20, 0); // 영업 종료 시간

    @Builder.Default
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToOne(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profileImage;

    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Double calculateAverageRating() {
        if (reviewList == null || reviewList.isEmpty()) {
            return 0.0;
        }
        double sum = reviewList.stream()
                .mapToInt(Review::getRating)
                .sum();
        return Math.round((sum / reviewList.size()) * 10) / 10.0; // 소수점 첫째 자리까지 반올림
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

    // 예약 가능한 시간 조회 메서드
    public List<LocalTime> getAvailableTimeSlots(LocalDate date, List<Reservation> reservations) {
        List<LocalTime> availableSlots = new ArrayList<>();

        if (reservations == null) {
            return availableSlots; // 예약 목록이 없으면 빈 리스트 반환
        }

        // 예약 목록을 예약 시간 기준으로 정렬
        reservations.sort(Comparator.comparing(Reservation::getStartTime));

        LocalTime currentTime = this.openingTime;

        while (currentTime.isBefore(this.closingTime)) {
            LocalTime nextSlot = currentTime.plusHours(1); // 1시간 단위로 확인

            // 예약이 있는지 확인
            LocalTime finalCurrentTime = currentTime;
            boolean isSlotBooked = reservations.stream()
                    .anyMatch(reservation ->
                            reservation.getReservationDate().equals(date) &&
                                    !(reservation.getEndTime().isBefore(finalCurrentTime) || reservation.getStartTime().isAfter(nextSlot)));

            if (!isSlotBooked) {
                availableSlots.add(currentTime);
            }
            currentTime = nextSlot;
        }

        return availableSlots;
    }



}
