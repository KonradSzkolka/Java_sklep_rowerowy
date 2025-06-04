package com.example.skleprowery.service;

import com.example.skleprowery.Cart;
import com.example.skleprowery.Model.Item;
import com.example.skleprowery.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    public enum ItemOperation {
        INCREASE,
        DECREASE,
        REMOVE
    }

    public final ItemRepository itemRepository;
    public final Cart cart;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void itemOperation(Long itemId, ItemOperation operation) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            switch (operation) {
                case INCREASE -> cart.addItem(item);
                case DECREASE -> cart.decreaseItem(item);
                case REMOVE -> cart.removeAllItems(item);
            }
        }
    }
}
