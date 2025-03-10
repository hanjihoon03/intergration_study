package com.intergration.study.client.order.domain.entity;

import lombok.Getter;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@Getter
public enum OrderStatus {
    ORDER_PENDING(Description.ORDER_PENDING),
    ORDER_COMPLETED(Description.ORDER_COMPLETED),
    ORDER_CANCELED(Description.ORDER_CANCELED),
    COOK_PREPARING(Description.COOK_PREPARING),
    DELIVERING(Description.DELIVERING),
    DELIVERY_COMPLETED(Description.DELIVERY_COMPLETED);

    private final String description;


    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static class Description {
        public static final String ORDER_PENDING = "ORDER_PENDING";
        public static final String ORDER_COMPLETED = "ORDER_COMPLETED";
        public static final String ORDER_CANCELED = "ORDER_CANCELED";
        public static final String COOK_PREPARING = "COOK_PREPARING";
        public static final String DELIVERING = "DELIVERING";
        public static final String DELIVERY_COMPLETED = "DELIVERY_COMPLETED";
    }
}
