package com.example.skleprowery.Controller;

import com.example.skleprowery.Cart;
import com.example.skleprowery.CartItem;
import com.example.skleprowery.Model.Item;
import com.example.skleprowery.repository.ItemRepository;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.StyleSheet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {


    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public HomeController(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("cart", cart); // aby mieć dostęp do cart.counter i cart.totalPrice w widoku
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            cart.addItem(item);
        }
        return "redirect:/";
    }
}
