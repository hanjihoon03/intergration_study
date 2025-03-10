package com.intergration.study.client.order.presentation.dto;

import com.intergration.study.client.order.domain.entity.Order;
import com.intergration.study.client.order.domain.entity.OrderProduct;
import java.util.List;
import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Builder
public record OrderProductRequestIds(Long productId,
                                     Integer orderQuantity) {

    public static OrderProductRequestIds of(OrderProduct orderProduct) {
        return OrderProductRequestIds.builder()
            .productId(orderProduct.getProductId())
            .orderQuantity(orderProduct.getOrderQuantity())
            .build();
    }

}
