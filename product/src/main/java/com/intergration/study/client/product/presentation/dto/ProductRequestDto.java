package com.intergration.study.client.product.presentation.dto;

import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Builder
public record ProductRequestDto(String productName,
                                Integer productPrice,
                                Integer quantity) {

}
