package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    @Getter
    private List<CartItem> cartItems = new ArrayList<>();

    @Getter
    private int counter = 0;

    @Getter
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(item))
        );
        recalculatePriceAndCounter();
    }

    public void decreaseItem(Item item) {
        Optional<CartItem> oCartItem = getCartItemByItem(item);
        oCartItem.ifPresent(cartItem -> {
            cartItem.decreaseCounter();
            if (cartItem.hasZeroItems()) {
                removeCartItem(cartItem);
            }
        });
        recalculatePriceAndCounter();
    }

    public void removeAllItems(Item item) {
        getCartItemByItem(item).ifPresent(this::removeCartItem);
        recalculatePriceAndCounter();
    }

    private void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    private Optional<CartItem> getCartItemByItem(Item item) {
        return cartItems.stream()
                .filter(ci -> ci.isEquals(item))
                .findFirst();
    }

    private void recalculatePriceAndCounter() {
        this.counter = cartItems.stream()
                .mapToInt(CartItem::getCounter)
                .sum();
        this.sum = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Dodana metoda na potrzeby zadania
    public void clearCart() {
        cartItems.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }
}
