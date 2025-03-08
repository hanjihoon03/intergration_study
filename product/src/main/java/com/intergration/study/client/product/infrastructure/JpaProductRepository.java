package com.intergration.study.client.product.infrastructure;

import com.intergration.study.client.product.domain.entity.Product;
import com.intergration.study.client.product.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 09.
 */
public interface JpaProductRepository extends JpaRepository<Product, Long>, ProductRepository {
}
