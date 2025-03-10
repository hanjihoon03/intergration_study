package com.intergration.study.client.product.presentation;

import com.intergration.study.client.product.application.service.ProductService;
import com.intergration.study.client.product.presentation.dto.order.OrderProductRequestIds;
import com.intergration.study.client.product.presentation.dto.ProductRequestDto;
import com.intergration.study.client.product.presentation.dto.ProductResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.findByProductId(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("productId") Long productId,
        @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.updateProduct(productId, requestDto));
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    //feign client 전용 컨트롤러 (별도로 만들어야 하는게 좋지만 일단 진행)
    @PostMapping("/buy")
    public List<ProductResponseDto> buyProducts(@RequestBody List<OrderProductRequestIds> request) {
        return productService.buy(request);
    }

    //주문이 배송 전 취소가 됐다는 가정
    @PostMapping("/products/cancel")
    public void cancelOrder(@RequestBody List<OrderProductRequestIds> request) {
        productService.cancelOrder(request);
    }


}
