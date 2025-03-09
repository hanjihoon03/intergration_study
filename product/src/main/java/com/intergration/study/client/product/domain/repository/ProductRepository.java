package com.intergration.study.client.product.domain.repository;

import com.intergration.study.client.product.domain.entity.Product;
import java.util.List;
import java.util.Optional;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long productId);

    List<Product> findAll();

    void deleteById(Long productId);

    List<Product> findAllById(List<Long> productIds);
}
