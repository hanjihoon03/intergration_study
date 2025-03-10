package com.intergration.study.client.order.presentation.dto;

import com.intergration.study.client.order.domain.entity.OrderProduct;
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

    public static OrderProductResponseDto of(OrderProduct orderProduct) {
        return OrderProductResponseDto.builder()
            .id(orderProduct.getId())
            .productId(orderProduct.getProductId())
            .productPrice(orderProduct.getProductPrice())
            .orderQuantity(orderProduct.getOrderQuantity())
            .build();
    }

}
