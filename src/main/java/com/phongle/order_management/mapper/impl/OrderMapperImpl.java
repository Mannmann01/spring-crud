package com.phongle.order_management.mapper.impl;

import com.phongle.order_management.domain.dto.OrderDto;
import com.phongle.order_management.domain.entity.Order;
import com.phongle.order_management.mapper.OrderDetailMapper;
import com.phongle.order_management.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderMapperImpl implements OrderMapper {

    private final OrderDetailMapper orderDetailMapper;

    public OrderMapperImpl(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public Order fromDto(OrderDto orderDto) {
        return new Order(
                orderDto.id(),
                orderDto.customerName(),
                orderDto.orderDate(),
                orderDto.status(),
                Optional.ofNullable(orderDto.orderDetails())
                        .map(orderDetails -> orderDetails.stream()
                                .map(orderDetailMapper::fromDto)
                                .toList()
                        ).orElse(null)
        );
    }

    // cách bình thường dễ hiểu hơn
//    @Override
//    public Order fromDto(OrderDto orderDto) {
//        List<OrderDetail> details = null;
//        if (orderDto.orderDetails() != null) {
//            details = new ArrayList<>();
//            for (OrderDetailDto dto : orderDto.orderDetails()) {
//                details.add(orderDetailMapper.fromDto(dto));
//            }
//        }
//
//        return new Order(
//                orderDto.id(),
//                orderDto.customerName(),
//                orderDto.orderDate(),
//                orderDto.status(),
//                details
//        );
//    }

    @Override
    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomerName(),
                order.getOrderDate(),
                order.getStatus(),
                Optional.ofNullable(order.getOrderDetails())
                        .map(orderDetails -> orderDetails.stream()
                                .map(orderDetailMapper::toDto)
                                .toList()
                        ).orElse(null)
        );
    }

    // cách bình thường dễ hiểu hơn
//    @Override
//    public OrderDto toDto(Order order) {
//        List<OrderDetailDto> details = null;
//        if (order.getOrderDetails() != null) {
//            details = new ArrayList<>();
//            for (OrderDetail entity : order.getOrderDetails()) {
//                details.add(orderDetailMapper.toDto(entity));
//            }
//        }
//
//        return new OrderDto(
//                order.getId(),
//                order.getCustomerName(),
//                order.getOrderDate(),
//                order.getStatus(),
//                details
//        );
//    }

}
