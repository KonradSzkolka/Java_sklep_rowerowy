package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class Cart {

    private final List<CartItem> cartItems = new ArrayList<>();
    private int totalItems = 0;
    private double totalPrice = 0.0;

    public void addItem(Item item) {
        CartItem cartItem = findOrCreateCartItem(item);
        cartItem.increaseCounter();
        recalculatePriceAndCounter();
    }

    public void removeItem(Item item) {
        CartItem cartItem = findCartItem(item);
        if (cartItem != null) {
            cartItem.decreaseCounter();
            if (cartItem.hasZeroItems()) {
                cartItems.remove(cartItem);
            }
            recalculatePriceAndCounter();
        }
    }

    private CartItem findOrCreateCartItem(Item item) {
        CartItem cartItem = findCartItem(item);
        if (cartItem == null) {
            cartItem = new CartItem(item);
            cartItems.add(cartItem);
        }
        return cartItem;
    }

    private CartItem findCartItem(Item item) {
        for (CartItem ci : cartItems) {
            if (ci.getItem().getId().equals(item.getId())) {
                return ci;
            }
        }
        return null;
    }

    private void recalculatePriceAndCounter() {
        int counter = 0;
        double price = 0.0;

        for (CartItem cartItem : cartItems) {
            counter += cartItem.getCounter();
            price += cartItem.getPrice();
        }

        this.totalItems = counter;
        this.totalPrice = price;
    }
}
