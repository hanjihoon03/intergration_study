package com.intergration.study.client.order.presentation;

import com.intergration.study.client.order.application.OrderService;
import com.intergration.study.client.order.presentation.dto.OrderRequestDto;
import com.intergration.study.client.order.presentation.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto requestDto) {
        return null;
    }


}
