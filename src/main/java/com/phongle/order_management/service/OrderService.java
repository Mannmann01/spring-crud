package com.phongle.order_management.service;

import com.phongle.order_management.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    Order updateOrder(Order order, Long id);
    void deleteOrder(Long id);
}
