package com.intergration.study.client.order.application.client;

import com.intergration.study.client.order.presentation.dto.OrderProductRequestIds;
import com.intergration.study.client.order.presentation.dto.product.ProductResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/products/buy")
    List<ProductResponseDto> verifyAndSubtractProducts(@RequestBody List<OrderProductRequestIds> request);

    @PostMapping("/products/cancel")
    void cancelOrder(@RequestBody List<OrderProductRequestIds> request);

}
