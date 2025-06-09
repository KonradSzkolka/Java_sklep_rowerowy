package com.example.skleprowery.service;

import com.example.skleprowery.Cart;
import com.example.skleprowery.Dto.OrderDto;
import com.example.skleprowery.Model.order.Order;
import com.example.skleprowery.Model.order.OrderItem;
import com.example.skleprowery.mapper.OrderMapper;
import com.example.skleprowery.repository.Order.OrderItemRepository;
import com.example.skleprowery.repository.Order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        order = orderRepository.save(order);
        List<OrderItem> orderItems = OrderMapper.mapToOrderItemList(cart, order);
        orderItemRepository.saveAll(orderItems);
        cart.clearCart(); // Wyczyszczenie koszyka po zapisaniu zamówienia
    }
}
