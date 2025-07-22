package com.phongle.order_management.mapper;

import com.phongle.order_management.domain.dto.OrderDetailDto;
import com.phongle.order_management.domain.entity.OrderDetail;

public interface OrderDetailMapper {
    OrderDetail fromDto(OrderDetailDto orderDetailDto);

    OrderDetailDto toDto(OrderDetail orderDetail);
}
