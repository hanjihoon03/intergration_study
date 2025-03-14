package com.intergration.study.client.product.domain.entity;

import com.intergration.study.client.product.presentation.dto.ProductRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 08.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer productPrice;
    private Integer quantity;


    public static Product of(ProductRequestDto requestDto) {
        return Product.builder()
            .productName(requestDto.productName())
            .productPrice(requestDto.productPrice())
            .quantity(requestDto.quantity())
            .build();
    }

    public void updateProduct(ProductRequestDto requestDto) {
        this.productName = requestDto.productName();
        this.productPrice = requestDto.productPrice();
        this.quantity = requestDto.productPrice();
    }


    //재고 차감
    public void subtractQuantity(int orderQuantity) {
        if (this.quantity < orderQuantity) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고: " + this.quantity);
        }
        this.quantity -= orderQuantity;
    }

    //재고 증가
    public void addQuantity(int orderQuantity) {
        this.quantity += orderQuantity;
    }


}
