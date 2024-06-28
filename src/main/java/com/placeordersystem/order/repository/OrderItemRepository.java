package com.placeordersystem.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.placeordersystem.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findByCustomerName(String customer);

    List<OrderItem> findByCustomerNameAndStatus(String customerName, String status);

    @Query(value = "SELECT SUM(total) FROM order_item WHERE customer_name = ?1 AND status = ?2", nativeQuery = true)
    double sumTotalByCustomerNameAndStatus(String customerName, String status);
}
