package com.example.skleprowery.Controller;

import com.example.skleprowery.Dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.skleprowery.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;

    @Autowired
    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String showCart() {
        return "cartView";
    }

    @GetMapping("/increase/{itemId}")
    public String increaseItem(@PathVariable("itemId") Long itemId, Model model) {
        cartService.itemOperation(itemId, CartService.ItemOperation.INCREASE);
        model.addAttribute("items", cartService.getAllItems());
        return "redirect:/order/cart";
    }

    @GetMapping("/decrease/{itemId}")
    public String decreaseItem(@PathVariable("itemId") Long itemId, Model model) {
        cartService.itemOperation(itemId, CartService.ItemOperation.DECREASE);
        model.addAttribute("items", cartService.getAllItems());
        return "cartView";
    }

    @GetMapping("/remove/{itemId}")
    public String removeAllOfItem(@PathVariable("itemId") Long itemId) {
        cartService.itemOperation(itemId, CartService.ItemOperation.REMOVE);
        return "cartView";
    }

    @GetMapping("/summary")
    public String showSummary(Model model) {
        model.addAttribute("cart", cartService.cart);
        return "summary";
    }

    @PostMapping("/summary")
    public String processSummaryForm(OrderDto orderDto) {
        cartService.cart.getCartItems().clear();
        return "redirect:/";
    }

    @PostMapping("/saveorder")
    public String saveOrder(OrderDto orderDto) {
        System.out.println(orderDto.getFirstName());
        System.out.println("hello");
        return "redirect:/";
    }
}