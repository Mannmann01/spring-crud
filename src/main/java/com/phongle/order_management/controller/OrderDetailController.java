package com.phongle.order_management.controller;

import com.phongle.order_management.domain.dto.OrderDetailDto;
import com.phongle.order_management.domain.entity.OrderDetail;
import com.phongle.order_management.mapper.OrderDetailMapper;
import com.phongle.order_management.service.OrderDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/orders/{order_id}/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailController(OrderDetailService orderDetailService, OrderDetailMapper orderDetailMapper) {
        this.orderDetailService = orderDetailService;
        this.orderDetailMapper = orderDetailMapper;
    }

    @GetMapping
    public List<OrderDetailDto> getOrderDetailsByOrderId(@PathVariable("order_id") Long orderId) {
        return orderDetailService.getOrderDetailsByOrderId(orderId)
                .stream()
                .map(orderDetailMapper::toDto)
                .toList();
    }

    @PostMapping
    public OrderDetailDto createOrderDetail(@PathVariable("order_id") Long orderId,
                                            @RequestBody OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = orderDetailMapper.fromDto(orderDetailDto);
        Long productId = orderDetailDto.product().id();
        return orderDetailMapper.toDto(orderDetailService.createOrderDetail(orderId, productId, orderDetail));
    }
}
