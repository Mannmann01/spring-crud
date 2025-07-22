package com.phongle.order_management.domain.dto;

public record ProductDto(
        Long id,
        String name,
        String description,
        int quantity,
        Double price
) {
}
