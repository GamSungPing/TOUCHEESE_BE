package com.rocket.toucheese_be.domain.like.controller;

import com.rocket.toucheese_be.domain.like.dto.LikeStudioListDto;
import com.rocket.toucheese_be.domain.like.dto.LikeStudioReqDto;
import com.rocket.toucheese_be.domain.like.service.LikeService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/like", produces = APPLICATION_JSON_VALUE)
public class LikeController {

    private final LikeService likeService;

    /**
     * 스튜디오 찜하기
     */
    @Operation(
            summary = "스튜디오 찜하기 [헤더 토큰 필요]",
            description = "멤버 ID와 스튜디오 ID를 사용하여 특정 스튜디오를 찜 목록에 추가합니다."
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Response<Void> addFavorite(@RequestBody LikeStudioReqDto likeStudioReqDto) {
        likeService.addLike(likeStudioReqDto.memberId(), likeStudioReqDto.studioId());
        return Response.of(SuccessCode.ADD_LIKE_SUCCESS);
    }


    /**
     * 찜한 스튜디오 목록 조회
     */
    @Operation(
            summary = "찜한 스튜디오 목록 조회 [헤더 토큰 필요]",
            description = "멤버 ID를 사용하여 찜한 스튜디오 목록을 조회합니다."
    )
    @GetMapping
    public Response<List<LikeStudioListDto>> getFavoriteStudios(@RequestParam Long memberId) {
        List<LikeStudioListDto> likedStudios = likeService.getLikeStudios(memberId);
        return Response.of(SuccessCode.GET_LIKED_STUDIOS_SUCCESS, likedStudios);
    }

    /**
     * 스튜디오 찜 삭제
     */
    @Operation(
            summary = "스튜디오 찜 삭제 [헤더 토큰 필요]",
            description = "멤버 ID와 스튜디오 ID를 사용하여 찜 목록에서 특정 스튜디오를 삭제합니다."
    )
    @DeleteMapping(value = "/delete/{studioId}")
    public Response<Void> removeFavorite(@RequestParam Long memberId, @PathVariable Long studioId) {
        likeService.removeLike(memberId, studioId);
        return Response.of(SuccessCode.REMOVE_LIKE_SUCCESS);
    }
}