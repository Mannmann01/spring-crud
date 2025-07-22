package com.phongle.order_management.controller;

import com.phongle.order_management.domain.dto.OrderDto;
import com.phongle.order_management.domain.entity.Order;
import com.phongle.order_management.mapper.OrderMapper;
import com.phongle.order_management.service.OrderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;


    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.createOrder(
                orderMapper.fromDto(orderDto)
        );
        return orderMapper.toDto(order);
    }

    @GetMapping(path = "/{order_id}")
    public Optional<OrderDto> getOrderById(@PathVariable("order_id") Long id) {
        return orderService.getOrderById(id)
                .map(orderMapper::toDto);
    }

    @PutMapping(path = "/{order_id}")
    public OrderDto updateOrder(@PathVariable("order_id") Long id, @RequestBody OrderDto orderDto) {
        Order order = orderService.updateOrder(
                orderMapper.fromDto(orderDto), id);
        return orderMapper.toDto(order);
    }

    @DeleteMapping(path = "/{order_id}")
    public void deleteOrder(@PathVariable("order_id") Long id) {
        orderService.deleteOrder(id);
    }
}
