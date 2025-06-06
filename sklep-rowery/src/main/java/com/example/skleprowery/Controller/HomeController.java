package com.example.skleprowery.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.skleprowery.service.CartService;

@Controller
public class HomeController {

    private final CartService cartService;

    @Autowired
    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", cartService.getAllItems());
        model.addAttribute("cartCounter", cartService.cart.getCounter());
        model.addAttribute("cartSum", cartService.cart.getSum());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model) {
        cartService.itemOperation(itemId, CartService.ItemOperation.INCREASE);
        model.addAttribute("items", cartService.getAllItems());
        model.addAttribute("cartCounter", cartService.cart.getCounter());
        model.addAttribute("cartSum", cartService.cart.getSum());
        return "redirect:/";
    }
}
