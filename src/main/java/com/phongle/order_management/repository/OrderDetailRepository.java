package com.phongle.order_management.repository;

import com.phongle.order_management.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
    Optional<OrderDetail> findByOrderIdAndProductId(Long orderId, Long productId);
}
