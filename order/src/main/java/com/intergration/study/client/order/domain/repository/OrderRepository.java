package com.intergration.study.client.order.domain.repository;

import com.intergration.study.client.order.domain.entity.Order;
import com.intergration.study.client.order.presentation.dto.OrderResponseDto;
import java.util.List;
import java.util.Optional;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long orderId);

    List<Order> findAll();
}
