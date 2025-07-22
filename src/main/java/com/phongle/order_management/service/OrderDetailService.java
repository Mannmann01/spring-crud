package com.phongle.order_management.service;

import com.phongle.order_management.domain.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);
    OrderDetail createOrderDetail(Long orderId, Long productId, OrderDetail orderDetail);
}
