package com.rocket.toucheese_be.domain.reservation.entity;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = false)
    private Studio studio;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    // 예약 상태 (예: 예약확정, 예약취소 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    // 예약 상태 변경 (취소)
    public void cancel() {
        this.status = ReservationStatus.예약취소;
    }

    // 예약 메서드
    public static Reservation createReservation(Member member, Studio studio, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return Reservation.builder()
                .member(member)
                .studio(studio)
                .reservationDate(date)
                .startTime(startTime)
                .endTime(endTime)
                .status(ReservationStatus.예약확정)
                .build();
    }
}
