package com.intergration.study.client.order.infrastructure;

import com.intergration.study.client.order.domain.entity.Order;
import com.intergration.study.client.order.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public interface JpaOrderRepository extends JpaRepository<Order, Long>, OrderRepository {

}
