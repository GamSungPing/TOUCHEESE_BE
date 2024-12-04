package com.rocket.toucheese_be.domain.reservation.controller;

import com.rocket.toucheese_be.domain.reservation.dto.AvailableTimeListDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationDto;
import com.rocket.toucheese_be.domain.reservation.dto.ReservationReqDto;
import com.rocket.toucheese_be.domain.reservation.service.ReservationService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/reservation", produces = APPLICATION_JSON_VALUE)
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 특정 예약 정보 조회
     */
    @Operation(
            summary = "특정 예약 정보 조회",
            description = "예약 ID를 사용하여 특정 예약의 상세 정보를 조회합니다. ")
    @GetMapping("/{reservationId}")
    public Response<ReservationDto> getReservationById(@PathVariable Long reservationId) {
        ReservationDto reservation = reservationService.getReservationById(reservationId);
        return Response.of(SuccessCode.GET_STUDIO_RESERVATION_ONE_SUCCESS, reservation);
    }

    /**
     * 스튜디오 예약 가능 시간 확인
     */
    @Operation(
            summary = "스튜디오 예약 가능 시간 확인",
            description = "특정 스튜디오의 예약 가능한 시간대를 조회합니다. " +
                    "date 파라미터는 필수입니다. 예: /{studioId}/available-slots?date=2024-12-01")
    @GetMapping("/{studioId}/available-slots")
    public Response<AvailableTimeListDto> getAvailableTimeSlots(
            @PathVariable Long studioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        AvailableTimeListDto availableSlots = reservationService.getAvailableTimeSlots(studioId, date);
        return Response.of(SuccessCode.GET_STUDIO_RESERVATION_AVAILABLE_LIST_SUCCESS, availableSlots);
    }

    /**
     * 특정 멤버가 예약한 모든 예약 목록 조회
     */
    @Operation(
            summary = "특정 멤버 예약 목록 조회",
            description = "특정 멤버가 예약한 모든 예약 목록을 조회합니다.")
    @GetMapping("/member/{memberId}")
    public Response<List<ReservationDto>> getReservationsByMember(@PathVariable Long memberId) {
        List<ReservationDto> reservations = reservationService.getReservationsByMember(memberId);
        return Response.of(SuccessCode.GET_MEMBER_RESERVATIONS_SUCCESS, reservations);
    }

    /**
     * 스튜디오 예약 생성
     */
    @Operation(
            summary = "새로운 예약 생성",
            description = "회원 ID와 스튜디오 ID, 예약 정보를 입력하여 새로운 예약을 생성합니다.")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Response<ReservationDto> createReservation(@RequestBody @Valid ReservationReqDto reservationReqDto) {
        ReservationDto reservation = reservationService.createReservation(reservationReqDto);
        return Response.of(SuccessCode.CREATE_RESERVATION_SUCCESS, reservation);
    }

    /**
     * 예약 취소
     */
    @Operation(
            summary = "예약 취소",
            description = "예약 ID와 멤버 ID를 사용하여 특정 예약을 취소합니다. " +
                    "memberId 파라미터는 필수입니다. 예: /{reservationId}/cancel?memberId=1")
    @DeleteMapping("/{reservationId}/cancel")
    public Response<Void> cancelReservation(@PathVariable Long reservationId, @RequestParam Long memberId) {
        reservationService.cancelReservation(reservationId, memberId);
        return Response.of(SuccessCode.CANCEL_RESERVATION_SUCCESS);
    }


}
