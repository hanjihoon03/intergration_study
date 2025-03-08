package com.intergration.study.client.order.presentation.dto;

import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Builder
public record OrderProductResponseDto(Long id,
                                      Long productId,
                                      Integer productPrice,
                                      Integer orderQuantity) {

}
