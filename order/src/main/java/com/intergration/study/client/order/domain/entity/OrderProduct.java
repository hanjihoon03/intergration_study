package com.intergration.study.client.order.domain.entity;

import com.intergration.study.client.order.presentation.dto.OrderRequestDto;
import com.intergration.study.client.order.presentation.dto.product.ProductResponseDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer productPrice;
    private Integer orderQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public static OrderProduct from(Order order,ProductResponseDto productResponseDto, Integer orderQuantity) {
        return OrderProduct.builder()
            .productId(productResponseDto.productId())
            .productPrice(productResponseDto.productPrice())
            .orderQuantity(orderQuantity)
            .order(order)
            .build();
    }

    protected void addOrder(Order order) {
        this.order = order;
    }
}
