package com.phongle.order_management.service.impl;

import com.phongle.order_management.domain.entity.Order;
import com.phongle.order_management.repository.OrderRepository;
import com.phongle.order_management.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        if(order.getId() != null) {
            throw new IllegalArgumentException("Order already has an ID");
        }
        if(order.getCustomerName() == null || order.getCustomerName().isBlank()) {
            throw new IllegalArgumentException("Order customer name cannot be empty");
        }
        LocalDateTime now = LocalDateTime.now();

        return orderRepository.save(new Order(
                null,
                order.getCustomerName(),
                now,
                order.getStatus(),
                null
        ));
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order updateOrder(Order order, Long id) {
        if(order.getId() == null) {
            throw new IllegalArgumentException("Order must have an ID");
        }

        if(!Objects.equals(order.getId(), id)) {
            throw new IllegalArgumentException("Attempting to change the ID of an order is not allowed");
        }

        Order existingOrder = orderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Order with ID " + id + " not found")
        );

        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
