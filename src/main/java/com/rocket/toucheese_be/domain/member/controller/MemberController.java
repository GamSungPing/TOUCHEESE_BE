package com.rocket.toucheese_be.domain.member.controller;

import com.rocket.toucheese_be.domain.member.service.MemberService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 멤버 이름 변경
     */
    @Operation(
            summary = "멤버 이름 변경",
            description = "멤버 ID를 사용하여 이름을 변경합니다. 새로운 이름은 중복될 수 없습니다."
    )
    @PutMapping("/{memberId}/name")
    public Response<Void> updateMemberName(@PathVariable Long memberId, @RequestParam String newName) {
        memberService.updateMemberName(memberId, newName);
        return Response.of(SuccessCode.UPDATE_MEMBER_NAME_SUCCESS);
    }
}
