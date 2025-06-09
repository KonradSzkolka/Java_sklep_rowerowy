package com.example.skleprowery.mapper;

import com.example.skleprowery.Cart;
import com.example.skleprowery.Dto.OrderDto;
import com.example.skleprowery.Model.order.Order;
import com.example.skleprowery.Model.order.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .created(LocalDateTime.now())
                .build();
    }

    public static List<OrderItem> mapToOrderItemList(Cart cart, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        cart.getCartItems().forEach(cartItem -> {
            // Zakładam, że CartItem ma metodę getItem() zwracającą Item, a Item ma getId()
            orderItems.add(
                    new OrderItem(
                            cartItem.getCounter(), // ilość sztuk
                            cartItem.getItem().getId(), // id produktu
                            order.getOrderId() // id zamówienia
                    )
            );
        });
        return orderItems;
    }
}
