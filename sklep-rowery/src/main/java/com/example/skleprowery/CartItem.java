package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLSession;
import java.util.ArrayList;
import java.util.List;


@Getter
public class CartItem {

    private final Item item;
    private int counter;
    private double price;

    public CartItem(Item item) {
        this.item = item;
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

