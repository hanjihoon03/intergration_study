package com.intergration.study.client.order.presentation.dto;

import com.intergration.study.client.order.domain.entity.Order;
import com.intergration.study.client.order.domain.entity.OrderStatus;
import java.util.List;
import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Builder
public record OrderResponseDto(Long orderId,
    List<OrderProductResponseDto> orderProductResponseDtoList,
                               Long userId,
                               OrderStatus orderStatus,
                               String orderRequest) {
    public static OrderResponseDto from(Order order, List<OrderProductResponseDto> dtoList) {
        return OrderResponseDto.builder()
            .orderId(order.getId())
            .userId(order.getUserId())
            .orderStatus(order.getOrderStatus())
            .orderRequest(order.getOrderRequest())
            .orderProductResponseDtoList(dtoList)
            .build();
    }

}
