package com.intergration.study.client.product.application.service;

import com.intergration.study.client.product.domain.entity.Product;
import com.intergration.study.client.product.domain.repository.ProductRepository;
import com.intergration.study.client.product.presentation.dto.order.OrderProductRequestIds;
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

    @Override
    public List<ProductResponseDto> buy(List<OrderProductRequestIds> request) {
        List<Product> products = checkProduct(request);

        verifyQuantity(request, products);


        return products.stream().map(ProductResponseDto::of).toList();

    }

    @Override
    public void cancelOrder(List<OrderProductRequestIds> request) {
        List<Product> products = checkProduct(request);
        // 요청에 맞는 수량을 증가시키기
        for (OrderProductRequestIds req : request) {

            Product product = products.stream()
                .filter(p -> p.getId().equals(req.productId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

            product.addQuantity(req.orderQuantity());
        }

    }

    private List<Product> checkProduct(List<OrderProductRequestIds> request) {
        List<Long> productIds = request.stream()
            .map(OrderProductRequestIds::productId)
            .toList();
        List<Product> products = productRepository.findAllById(productIds);

        // 존재하지 않는 상품이 있는지 확인
        if (products.size() != productIds.size()) {
            throw new IllegalArgumentException("주문한 상품 중 일부가 존재하지 않습니다.");
        }

        return products;
    }

    // 재고 검증 및 차감 (재고 부족한 상품이 있는 경우 예외 처리)
    private void verifyQuantity(List<OrderProductRequestIds> request, List<Product> products) {

        for (OrderProductRequestIds req : request) {
            Product product = products.stream()
                .filter(p -> p.getId().equals(req.productId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

            product.subtractQuantity(req.orderQuantity());
        }
    }
}
