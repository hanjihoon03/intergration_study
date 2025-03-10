package com.intergration.study.client.order.application.service;

import com.intergration.study.client.order.application.client.ProductClient;
import com.intergration.study.client.order.domain.entity.Order;
import com.intergration.study.client.order.domain.entity.OrderProduct;
import com.intergration.study.client.order.domain.repository.OrderRepository;
import com.intergration.study.client.order.presentation.dto.OrderProductRequestIds;
import com.intergration.study.client.order.presentation.dto.OrderProductResponseDto;
import com.intergration.study.client.order.presentation.dto.OrderRequestDto;
import com.intergration.study.client.order.presentation.dto.OrderResponseDto;
import com.intergration.study.client.order.presentation.dto.product.ProductResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        List<ProductResponseDto> productResponseDtos = productClient.verifyAndSubtractProducts(
            requestDto.orderProductRequestIds());

        Order order = Order.from(requestDto, productResponseDtos, requestDto.userId());
        orderRepository.save(order);

        List<OrderProductResponseDto> orderProductResponseDtoList = order.getOrderProductsList().stream()
            .map(OrderProductResponseDto::of)
            .toList();

        return OrderResponseDto.from(order, orderProductResponseDtoList);
    }

    @Override
    public OrderResponseDto findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
            new IllegalArgumentException("주문이 존재하지 않습니다."));
        List<OrderProductResponseDto> orderProductlist = order.getOrderProductsList().stream()
            .map(OrderProductResponseDto::of).toList();

        return OrderResponseDto.from(order, orderProductlist);
    }

    @Override
    public List<OrderResponseDto> findAll() {
        List<Order> orderList = orderRepository.findAll();


        return orderList.stream()
            .map(order -> {
                List<OrderProductResponseDto> orderProductList = order.getOrderProductsList().stream()
                    .map(OrderProductResponseDto::of)
                    .toList();
                return OrderResponseDto.from(order, orderProductList);
            })
            .toList();
    }


    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("취소할 주문이 없습니다."));
        List<OrderProductRequestIds> orderProductRequestIds = order.getOrderProductsList().stream()
            .map(OrderProductRequestIds::of).toList();

        productClient.cancelOrder(orderProductRequestIds);
    }
}
