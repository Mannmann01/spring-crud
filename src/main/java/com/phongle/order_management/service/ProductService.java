package com.phongle.order_management.service;

import com.phongle.order_management.domain.entity.Order;
import com.phongle.order_management.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Optional<Product> getProductById(Long id);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
}
