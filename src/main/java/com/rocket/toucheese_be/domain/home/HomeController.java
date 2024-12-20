package com.rocket.toucheese_be.domain.home;

import com.rocket.toucheese_be.domain.reservation.dto.ReservationAdminList;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HomeController {

    private final ReservationService reservationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String home() {
        return "admin/home";
    }

    // 예약 전체 조회 API (페이징 처리)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all-reservations")
    public String getAllReservations(@RequestParam(defaultValue = "0") int page, Model model) {
        // Pageable 객체에서 page와 size를 설정하여 페이징 처리된 예약 목록 조회
        Pageable pageable = PageRequest.of(page, 10);  // size를 10으로 설정
        Page<ReservationAdminList> reservations = reservationService.getAllReservationsSortedByCreatedAt(pageable);
        model.addAttribute("reservations", reservations);
        return "admin/all-reservations"; // 반환할 뷰 이름
    }

    // 예약 대기 상태 목록 조회 (페이징 처리)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/waiting-reservations")
    public String getWaitingReservations(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        // 페이징 처리된 대기 상태 예약 목록 조회
        Page<ReservationAdminList> waitingReservations = reservationService.getReservationsByStatus(ReservationStatus.waiting, pageable);
        model.addAttribute("reservations", waitingReservations);
        return "admin/waiting-reservations";
    }

    // 예약 승인
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reservations/{reservationId}/confirm")
    public String confirmReservation(@PathVariable Long reservationId) {
        reservationService.confirmReservation(reservationId);
        return "redirect:/admin/waiting-reservations"; // 목록으로 리다이렉트
    }

    // 예약 거절
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reservations/{reservationId}/cancel")
    public String cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/admin/waiting-reservations"; // 목록으로 리다이렉트
    }
}
