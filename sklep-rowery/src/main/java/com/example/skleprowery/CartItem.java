package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLSession;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int counter;
    private double price;

    public CartItem(Item item, Cart cart) {
        this.item = item;
        this.cart = cart;
        this.counter = 0;
        this.price = item.getPrice().doubleValue();
    }

    public void increaseCounter() {
        counter++;
        recalculatePrice();
    }

    public void decreaseCounter() {
        if (counter > 0) {
            counter--;
            recalculatePrice();
        }
    }

    public boolean hasZeroItems() {
        return counter == 0;
    }

    private void recalculatePrice() {
        this.price = item.getPrice().doubleValue() * counter;
    }
}

