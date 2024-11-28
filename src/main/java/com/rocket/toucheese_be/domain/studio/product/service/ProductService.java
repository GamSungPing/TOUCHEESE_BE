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
                    int reviewCount = productRepository.countReviewsByProductId(product.getId());
                    return new ProductDto(product, reviewCount);})
                .collect(Collectors.toList());
    }

    public ProductDto getProductDetail(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) return new ProductDto();

        int reviewCount = productRepository.countReviewsByProductId(id);
        return new ProductDto(product, reviewCount);
    }
}
