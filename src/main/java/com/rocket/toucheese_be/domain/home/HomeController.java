package com.rocket.toucheese_be.domain.home;

import com.rocket.toucheese_be.domain.reservation.dto.ReservationAdminList;
import com.rocket.toucheese_be.domain.reservation.entity.ReservationStatus;
import com.rocket.toucheese_be.domain.reservation.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    public String getAllReservations(@RequestParam(defaultValue = "0") int page, Model model, HttpSession session) {
        // Pageable 객체에서 page와 size를 설정하여 페이징 처리된 예약 목록 조회
        Pageable pageable = PageRequest.of(page, 10);  // size를 10으로 설정
        Page<ReservationAdminList> reservations = reservationService.getAllReservationsSortedByCreatedAt(pageable);
        model.addAttribute("reservations", reservations);
        if (Boolean.TRUE.equals(session.getAttribute("isAdmin"))) {
            return "admin/all-reservations"; // 반환할 뷰 이름
        }
        return "redirect:/admin/login";
    }

    // 예약 대기 상태 목록 조회 (페이징 처리)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/waiting-reservations")
    public String getWaitingReservations(@RequestParam(defaultValue = "0") int page, Model model, HttpSession session) {
        Pageable pageable = PageRequest.of(page, 10);
        // 페이징 처리된 대기 상태 예약 목록 조회
        Page<ReservationAdminList> waitingReservations = reservationService.getReservationsByStatus(ReservationStatus.waiting, pageable);
        model.addAttribute("reservations", waitingReservations);
        if (Boolean.TRUE.equals(session.getAttribute("isAdmin"))) {
            return "admin/waiting-reservations";
        }
        return "redirect:/admin/login";
    }

    // 예약 승인
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reservations/{reservationId}/confirm")
    public String confirmReservation(@PathVariable Long reservationId, HttpSession session) {
        reservationService.confirmReservation(reservationId);
        if (Boolean.TRUE.equals(session.getAttribute("isAdmin"))) {
            return "redirect:/admin/waiting-reservations"; // 목록으로 리다이렉트
        }
        return "redirect:/admin/login";
    }

    // 예약 거절
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reservations/{reservationId}/cancel")
    public String cancelReservation(@PathVariable Long reservationId, HttpSession session) {
        reservationService.cancelReservation(reservationId);
        if (Boolean.TRUE.equals(session.getAttribute("isAdmin"))) {
            return "redirect:/admin/waiting-reservations"; // 목록으로 리다이렉트
        }
        return "redirect:/admin/login";
    }

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.password}")
    private String adminPW;

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/login"; // login.html 템플릿
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String name,
            @RequestParam String refreshToken,
            HttpSession session,
            Model model) {

        if (adminName.equals(name) && adminPW.equals(refreshToken)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/admin"; // home으로 리다이렉트
        }
        model.addAttribute("error", "비밀번호가 틀렸습니다");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
