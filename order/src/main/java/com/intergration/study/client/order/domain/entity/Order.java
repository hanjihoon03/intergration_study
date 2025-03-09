package com.intergration.study.client.order.domain.entity;

import com.intergration.study.client.order.presentation.dto.OrderProductRequestDto;
import com.intergration.study.client.order.presentation.dto.OrderProductRequestIds;
import com.intergration.study.client.order.presentation.dto.OrderRequestDto;
import com.intergration.study.client.order.presentation.dto.product.ProductResponseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private OrderStatus orderStatus;

    private String orderRequest;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProductsList = new ArrayList<>();


    public static Order from(OrderRequestDto orderRequestDto, List<ProductResponseDto> productResponseDtos, Long userId) {
        Order order = Order.builder()
            .userId(userId)
            .orderStatus(orderRequestDto.orderStatus())
            .orderRequest(orderRequestDto.orderRequest())
            .build();

        List<Integer> orderQuantityList = orderRequestDto.orderProductRequestIds().stream()
            .map(OrderProductRequestIds::orderQuantity)
            .toList();

        for (int i = 0; i < productResponseDtos.size(); i++) {
            order.addOrderProduct(productResponseDtos.get(i), orderQuantityList.get(i));
        }
        return order;
    }

    private void addOrderProduct(ProductResponseDto productResponseDto, Integer orderQuantity) {
        OrderProduct orderProduct = OrderProduct.from(this, productResponseDto, orderQuantity);
        this.orderProductsList.add(orderProduct);
    }


}
