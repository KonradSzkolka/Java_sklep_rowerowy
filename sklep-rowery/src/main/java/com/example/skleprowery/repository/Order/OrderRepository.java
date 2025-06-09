package com.example.skleprowery.repository.Order;

import com.example.skleprowery.Model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}