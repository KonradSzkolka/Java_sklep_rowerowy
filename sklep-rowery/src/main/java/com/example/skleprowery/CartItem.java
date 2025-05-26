package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import lombok.Getter;

@Getter
public class CartItem {

    private final Item item;
    private int counter;
    private double price;

    public CartItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
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