package com.phongle.order_management.mapper.impl;

import com.phongle.order_management.domain.dto.OrderDetailDto;
import com.phongle.order_management.domain.entity.OrderDetail;
import com.phongle.order_management.mapper.OrderDetailMapper;
import com.phongle.order_management.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    private final ProductMapper productMapper;

    public OrderDetailMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public OrderDetail fromDto(OrderDetailDto orderDetailDto) {
        return new OrderDetail(
                orderDetailDto.id(),
                orderDetailDto.quantity(),
                orderDetailDto.price(),
                null,
                null
        );
    }

    @Override
    public OrderDetailDto toDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getId(),
                orderDetail.getQuantity(),
                orderDetail.getPrice(),
                orderDetail.getProduct() != null
                        ? productMapper.toDto(orderDetail.getProduct())
                        : null
        );
    }
}
