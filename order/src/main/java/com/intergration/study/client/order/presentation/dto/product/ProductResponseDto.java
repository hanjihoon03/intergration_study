package com.intergration.study.client.order.presentation.dto.product;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public record ProductResponseDto(Long productId,
                                 String productName,
                                 Integer productPrice,
                                 Integer quantity) {

}
