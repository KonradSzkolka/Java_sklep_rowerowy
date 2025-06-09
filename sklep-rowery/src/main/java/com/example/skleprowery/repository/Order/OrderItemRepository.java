package com.example.skleprowery.repository.Order;

import com.example.skleprowery.Model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
