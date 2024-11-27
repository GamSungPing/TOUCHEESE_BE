package com.rocket.toucheese_be.domain.studio.product.service;

import com.rocket.toucheese_be.domain.studio.product.dto.ProductDto;
import com.rocket.toucheese_be.domain.studio.product.entity.Product;
import com.rocket.toucheese_be.domain.studio.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> getProductListByStudioId(Long studioId) {
        List<Product> productList = productRepository.findProductsByStudioId(studioId);

        return productList.stream()
                .map(product -> {
                    int reviewCount = productRepository.countReviewsByProductId(product.getId()); // 리뷰 개수 조회
                    return new ProductDto(product, reviewCount);})
                .collect(Collectors.toList());
    }
}
