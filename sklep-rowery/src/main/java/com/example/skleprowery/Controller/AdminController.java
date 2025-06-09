package com.example.skleprowery.Controller;

import com.example.skleprowery.Model.order.Order;
import com.example.skleprowery.repository.Order.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    private final OrderRepository orderRepository;

    public AdminController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/admin/orders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}
