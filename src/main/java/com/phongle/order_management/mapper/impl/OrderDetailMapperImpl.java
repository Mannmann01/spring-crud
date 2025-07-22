package com.phongle.order_management.mapper.impl;

import com.phongle.order_management.domain.dto.OrderDetailDto;
import com.phongle.order_management.domain.entity.OrderDetail;
import com.phongle.order_management.domain.entity.Product;
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

        if(orderDetailDto == null) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setId(orderDetailDto.id());
        orderDetail.setQuantity(orderDetailDto.quantity());
        orderDetail.setPrice(orderDetailDto.price());

        orderDetail.setOrder(null);

        if(orderDetailDto.product() != null && orderDetailDto.product().id() != null) {
            Product product = new Product();
            product.setId(orderDetailDto.product().id());
            orderDetail.setProduct(product);
        } else {
            orderDetail.setProduct(null);
        }

        return orderDetail;
    }

    @Override
    public OrderDetailDto toDto(OrderDetail orderDetail) {

        if (orderDetail == null) {
            return null;
        }

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
