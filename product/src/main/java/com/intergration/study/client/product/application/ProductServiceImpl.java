package com.intergration.study.client.product.application;

import com.intergration.study.client.product.domain.entity.Product;
import com.intergration.study.client.product.domain.repository.ProductRepository;
import com.intergration.study.client.product.presentation.dto.ProductRequestDto;
import com.intergration.study.client.product.presentation.dto.ProductResponseDto;
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
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product saveProduct = productRepository.save(Product.of(requestDto));
        return ProductResponseDto.of(saveProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
            new IllegalArgumentException("상품을 찾을 수 없습니다."));
        return ProductResponseDto.of(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductResponseDto::of).toList();
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
            new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.updateProduct(requestDto);

        return ProductResponseDto.of(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
