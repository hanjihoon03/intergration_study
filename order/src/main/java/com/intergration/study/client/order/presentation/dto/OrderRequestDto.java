package com.intergration.study.client.order.presentation.dto;

import com.intergration.study.client.order.domain.entity.OrderStatus;
import java.util.List;
import lombok.Builder;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Builder
public record OrderRequestDto(
    List<OrderProductRequestIds> orderProductRequestIds,
    Long userId,
    OrderStatus orderStatus,
    String orderRequest
) {

}
