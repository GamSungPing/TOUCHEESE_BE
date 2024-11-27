package com.rocket.toucheese_be.domain.studio.product.controller;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.product.service.ProductService;
import com.rocket.toucheese_be.global.response.Response;
import com.rocket.toucheese_be.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/api/v1/product", produces = APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // TODO: 스튜디오 상세 조회시 ProductDto가 리스트로 함께 가도록 수정할 것
    @Operation(summary = "특정 스튜디오의 상품 목록 조회", description = "스튜디오 ID를 통해 특정 스튜디오의 상품 목록을 조회합니다.")
    @GetMapping("/studio/{studioId}")
    public Response<List<ProductDto>> getProductList(@PathVariable("studioId") Long studioId) {
        List<ProductDto> productListDto = productService.getProductListByStudioId(studioId);
        return Response.of(SuccessCode.GET_PRODUCT_LIST_BY_STUDIO_SUCCESS, productListDto);
    }

    // TODO: 프로덕트 상세 DTO 보내는 메서드 필요

}