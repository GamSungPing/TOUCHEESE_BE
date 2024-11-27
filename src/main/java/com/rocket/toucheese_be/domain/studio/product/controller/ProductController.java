//package com.rocket.toucheese_be.domain.studio.product.controller;
//
//import com.rocket.toucheese_be.domain.studio.product.dto.ProductListDto;
//import com.rocket.toucheese_be.domain.studio.product.entity.Product;
//import com.rocket.toucheese_be.domain.studio.product.service.ProductService;
//import com.rocket.toucheese_be.global.response.Response;
//import com.rocket.toucheese_be.global.response.SuccessCode;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RequestMapping(value = "/api/v1/product", produces = APPLICATION_JSON_VALUE)
//@RestController
//@RequiredArgsConstructor
//public class ProductController {
//    private final ProductService productService;
//
//    private static List<ProductListDto> toProductListDto(List<Product> products) {
//        return products.stream()
//                .map(ProductListDto::new)
//                .toList();
//    }
//
//    @Operation(summary = "특정 스튜디오의 상품 목록 조회", description = "스튜디오 ID를 통해 특정 스튜디오의 상품 목록을 조회합니다.")
//    @GetMapping("/studio/{studioId}")
//    public Response<List<ProductListDto>> getProductList(@PathVariable("studioId") Long studioId) {
//        List<Product> productList = productService.getProductListByStudioId(studioId);
//        List<ProductListDto> productListDto = toProductListDto(productList);
//        return Response.of(SuccessCode.GET_PRODUCT_LIST_BY_STUDIO_SUCCESS, productListDto);
//    }
//
//}