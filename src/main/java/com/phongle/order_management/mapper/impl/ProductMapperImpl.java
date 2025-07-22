package com.phongle.order_management.mapper.impl;

import com.phongle.order_management.domain.dto.ProductDto;
import com.phongle.order_management.domain.entity.Product;
import com.phongle.order_management.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product fromDto(ProductDto productDto) {
        return new Product(
                productDto.id(),
                productDto.name(),
                productDto.description(),
                productDto.price(),
                productDto.quantity(),
                null
        );

    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }
}
