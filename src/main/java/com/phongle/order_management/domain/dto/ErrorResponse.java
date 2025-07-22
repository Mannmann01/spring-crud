package com.phongle.order_management.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
