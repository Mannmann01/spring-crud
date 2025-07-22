package com.phongle.order_management.domain.dto;

import com.phongle.order_management.domain.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        String customerName,
        LocalDateTime orderDate,
        Status status,
        List<OrderDetailDto> orderDetails
) {
}
