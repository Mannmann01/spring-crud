package com.phongle.order_management.mapper;

import com.phongle.order_management.domain.dto.ProductDto;
import com.phongle.order_management.domain.entity.Product;

public interface ProductMapper {
    Product fromDto(ProductDto productDto);

    ProductDto toDto(Product product);
}
