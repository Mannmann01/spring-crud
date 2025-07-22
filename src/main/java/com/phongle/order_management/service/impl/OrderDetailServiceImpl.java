package com.phongle.order_management.service.impl;

import com.phongle.order_management.domain.entity.Order;
import com.phongle.order_management.domain.entity.OrderDetail;
import com.phongle.order_management.domain.entity.Product;
import com.phongle.order_management.repository.OrderDetailRepository;
import com.phongle.order_management.repository.OrderRepository;
import com.phongle.order_management.repository.ProductRepository;
import com.phongle.order_management.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public OrderDetail createOrderDetail(Long orderId, Long productId, OrderDetail orderDetail) {
        if(orderDetail.getId() != null) {
            throw new IllegalArgumentException("OrderDetail already has an ID");
        }

        if(orderDetail.getQuantity() <= 0) {
            throw new IllegalArgumentException("OrderDetail quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + orderId + " not found"));

        if(orderDetail.getQuantity() > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough quantity of product " + product.getName() + " in stock");
        }

        Double price = product.getPrice();

        Optional<OrderDetail> existingOrderDetail = orderDetailRepository
                .findByOrderIdAndProductId(orderId, productId);

        if(existingOrderDetail.isPresent()) {
            OrderDetail existing = existingOrderDetail.get();

            int newQuantity = existing.getQuantity() + orderDetail.getQuantity();

            if(newQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("Not enough quantity of product " + product.getName() + " in stock");
            }

            existing.setQuantity(newQuantity);
            orderDetailRepository.save(existing);

            product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
            productRepository.save(product);

            return existing;
        }

        product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
        productRepository.save(product);

        OrderDetail newOrderDetail = new OrderDetail(
                null,
                orderDetail.getQuantity(),
                price,
                order,
                product);
        return orderDetailRepository.save(newOrderDetail);
    }
}
