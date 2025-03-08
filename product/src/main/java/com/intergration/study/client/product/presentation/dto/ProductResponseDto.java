package com.intergration.study.client.product.presentation.dto;

import com.intergration.study.client.product.domain.entity.Product;
import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Builder
public record ProductResponseDto(Long productId,
                                 String productName,
                                 Integer productPrice,
                                 Integer quantity) {

    public static ProductResponseDto of(Product product) {
        return ProductResponseDto.builder()
            .productId(product.getId())
            .productName(product.getProductName())
            .productPrice(product.getProductPrice())
            .quantity(product.getQuantity())
            .build();
    }

}
