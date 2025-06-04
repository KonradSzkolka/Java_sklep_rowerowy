package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartItem {

    private final Item item;
    private int counter;
    private BigDecimal price;

    public CartItem(Item item) {
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public void decreaseCounter() {
        if (counter > 0) {
            counter--;
            recalculate();
        }
    }

    public boolean hasZeroItems() {
        return counter == 0;
    }

    private void recalculate() {
        this.price = item.getPrice().multiply(BigDecimal.valueOf(counter));
    }

    public boolean isEquals(Item item) {
        return this.item.getId().equals(item.getId());
    }
}