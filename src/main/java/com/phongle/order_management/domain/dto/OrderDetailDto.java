package com.phongle.order_management.domain.dto;

public record OrderDetailDto(
        Long id,
        int quantity,
        Double price,
        ProductDto product
) {
}
