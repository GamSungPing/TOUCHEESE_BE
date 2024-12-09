package com.rocket.toucheese_be.domain.home;

import com.rocket.toucheese_be.domain.reservation.dto.ReservationAdminList;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HomeController {

    private final ReservationService reservationService;

    // 예약 대기 상태 목록 조회
    @GetMapping("/reservations")
    public String getWaitingReservations(Model model) {
        List<ReservationAdminList> waitingReservations = reservationService.getReservationsByStatus(ReservationStatus.waiting);
        model.addAttribute("reservations", waitingReservations);
        return "admin/reservations";
    }

    // 예약 승인
    @PostMapping("/reservations/{reservationId}/confirm")
    public String confirmReservation(@PathVariable Long reservationId) {
        reservationService.confirmReservation(reservationId);
        return "redirect:/admin/reservations"; // 목록으로 리다이렉트
    }

    // 예약 거절
    @PostMapping("/reservations/{reservationId}/cancel")
    public String cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/admin/reservations"; // 목록으로 리다이렉트
    }
}
