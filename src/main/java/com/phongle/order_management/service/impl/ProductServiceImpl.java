package com.phongle.order_management.service.impl;

import com.phongle.order_management.domain.entity.Product;
import com.phongle.order_management.repository.ProductRepository;
import com.phongle.order_management.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        if(product.getId() != null) {
            throw new IllegalArgumentException("Product already has an ID");
        }
        if(product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if(product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        if(product.getQuantity() <= 0) {
            throw new IllegalArgumentException("Product quantity must be greater than 0");
        }
        return productRepository.save(new Product(
                null,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                null
        ));
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        if(product.getId() == null) {
            throw new IllegalArgumentException("Product must have an ID");
        }

        if(!Objects.equals(product.getId(), id)) {
            throw new IllegalArgumentException("Attempting to change the ID of a product is not allowed");
        }

        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product with ID " + id + " not found")
        );

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
