package com.intergration.study.client.product.application;

import com.intergration.study.client.product.presentation.dto.ProductRequestDto;
import com.intergration.study.client.product.presentation.dto.ProductResponseDto;
import java.util.List;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    ProductResponseDto findByProductId(Long productId);

    List<ProductResponseDto> findAll();

    ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto);

    void deleteProduct(Long productId);
}
