package com.phongle.order_management.controller;

import com.phongle.order_management.domain.dto.ProductDto;
import com.phongle.order_management.domain.entity.Product;
import com.phongle.order_management.mapper.ProductMapper;
import com.phongle.order_management.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(
                productMapper.fromDto(productDto)
        );
        return productMapper.toDto(product);
    }

    @GetMapping(path = "/{product_id}")
    public Optional<ProductDto> getProductById(@PathVariable("product_id") Long id) {
        return productService.getProductById(id)
                .map(productMapper::toDto);
    }

    @PutMapping(path = "/{product_id}")
    public ProductDto updateProduct(@PathVariable("product_id") Long id, @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(
                productMapper.fromDto(productDto), id);
        return productMapper.toDto(product);
    }

    @DeleteMapping(path = "/{product_id}")
    public void deleteProduct(@PathVariable("product_id") Long id) {
        productService.deleteProduct(id);
    }
}
