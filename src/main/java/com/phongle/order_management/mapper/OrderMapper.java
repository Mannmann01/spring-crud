package com.phongle.order_management.mapper;

import com.phongle.order_management.domain.dto.OrderDto;
import com.phongle.order_management.domain.entity.Order;

public interface OrderMapper {
    Order fromDto(OrderDto orderDto);

    OrderDto toDto(Order order);

}
