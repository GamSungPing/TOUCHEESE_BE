package com.rocket.toucheese_be.domain.studio.studio.entity;

import com.rocket.toucheese_be.domain.reservation.entity.Reservation;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
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
@ToString
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
    private List<Review> reviewList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();

    @Setter
    @Transient // 데이터베이스에 저장하지 않음
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private String address;

    @Column(length = 550)
    private String notice; // TODO : column 길이 길게 지정 필요 or TEXT

    private String holidays;

    @Builder.Default
    @Column
    private LocalTime openingTime = LocalTime.of(10, 0); // 영업 시작 시간

    @Builder.Default
    @Column
    private LocalTime closingTime = LocalTime.of(20, 0); // 영업 종료 시간

    @Builder.Default
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToOne(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profileImage;

    @Builder.Default
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Double calculateAverageRating() {
        if (reviewList == null || reviewList.isEmpty()) {
            return 0.0;
        }
        double sum = reviewList.stream()
                .mapToDouble(Review::getRating)
                .sum();
        double average = sum / reviewList.size();
        return Math.round(average * 10) / 10.0; // 소수점 첫째 자리에서 반올림
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
            reservations = new ArrayList<>();
        }

        // 상태가 cancel이 아닌 예약만 필터링
        List<Reservation> activeReservations = reservations.stream()
                .filter(reservation -> reservation.getReservationDate().equals(date))
                .filter(reservation -> reservation.getStatus() != ReservationStatus.cancel)
                .sorted(Comparator.comparing(Reservation::getStartTime))
                .toList();

        LocalTime currentTime = this.openingTime;

        while (currentTime.isBefore(this.closingTime)) {
            LocalTime nextSlot = currentTime.plusHours(1);

            // nextSlot이 closingTime을 초과하면 루프 종료
            if (nextSlot.isAfter(this.closingTime)) {
                break;
            }

            // 예약 여부 확인
            LocalTime finalCurrentTime = currentTime;
            boolean isSlotBooked = activeReservations.stream()
                    .anyMatch(reservation ->
                            finalCurrentTime.isBefore(reservation.getEndTime()) &&
                                    nextSlot.isAfter(reservation.getStartTime())
                    );

            if (!isSlotBooked) {
                availableSlots.add(currentTime);
            }

            currentTime = nextSlot;
        }

        // closingTime이 정확히 24:00:00으로 간주되면 자정을 추가
        if (this.closingTime.equals(LocalTime.MAX)) {
            availableSlots.add(LocalTime.MIDNIGHT);
        }

        return availableSlots;
    }





}
