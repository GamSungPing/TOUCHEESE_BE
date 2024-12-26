package com.rocket.toucheese_be.domain.studio.product.service;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDetailDto;
import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import com.rocket.toucheese_be.domain.studio.product.repository.ProductRepository;
import com.rocket.toucheese_be.global.response.CustomException;
import com.rocket.toucheese_be.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    // 특정 스튜디오의 상품 목록 조회
    public List<ProductDto> getProductListByStudioId(Long studioId) {
        List<Product> productList = productRepository.findProductsByStudioId(studioId);

        if (productList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_STUDIO_PRODUCT);
        }

        // 각 프로덕트 별 리뷰 개수 가져오기
        return productList.stream()
                .map(product -> {
                    int reviewCount = productRepository.countReviewsByProductId(product.getId());
                    return new ProductDto(product, reviewCount);
                })
                .collect(Collectors.toList());
    }

    // 특정 상품 상세 조회
    public ProductDetailDto getProductDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STUDIO_PRODUCT_DETAIL));

        return new ProductDetailDto(product);
    }
}
