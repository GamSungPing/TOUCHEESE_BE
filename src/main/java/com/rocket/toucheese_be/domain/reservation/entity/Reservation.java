package com.rocket.toucheese_be.domain.reservation.entity;

import com.rocket.toucheese_be.domain.member.entity.Member;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationReqDto;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private Integer totalPrice;

    private String productName;

    private String productOption;

    private LocalDateTime createdAt;

    private String phoneNumber;

    private String email;

    // 예약 상태 (예: 예약확정, 예약대기, 예약취소)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    // 예약 상태 변경 (예약취소)
    public void cancel() {
        this.status = ReservationStatus.cancel;
    }

    // 예약 상태 변경 (예약확정)
    public void confirm() {
        this.status = ReservationStatus.confirm;
    }

    // 예약 상태 변경 (완료)
    public void complete() {
        this.status = ReservationStatus.complete;
    }


    // 예약 메서드
    public static Reservation create(Member member, Studio studio, ReservationReqDto dto) {
        return Reservation.builder()
                .member(member)
                .studio(studio)
                .reservationDate(dto.reservationDate())
                .startTime(dto.reservationTime())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .endTime(dto.reservationTime().plusMinutes(59).plusSeconds(59))
                .totalPrice(dto.totalPrice())
                .productName(dto.productName())
                .productOption(dto.productOption())
                .status(ReservationStatus.waiting)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
