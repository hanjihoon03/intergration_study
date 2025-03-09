package com.intergration.study.client.order.application.service;

import com.intergration.study.client.order.presentation.dto.OrderRequestDto;
import com.intergration.study.client.order.presentation.dto.OrderResponseDto;
import java.util.List;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);

    OrderResponseDto findById(Long orderId);

    List<OrderResponseDto> findAll();

    void deleteOrder(Long orderId);
}
