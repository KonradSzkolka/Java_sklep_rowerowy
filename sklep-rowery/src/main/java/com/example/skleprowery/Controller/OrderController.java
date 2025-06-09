package com.example.skleprowery.Controller;

import com.example.skleprowery.Dto.OrderDto;
import com.example.skleprowery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.skleprowery.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        // Przekazujemy obiekt cart do widoku
        model.addAttribute("cart", cartService.cart);
        return "cartView";
    }

    @GetMapping("/increase/{itemId}")
    public String increaseItem(@PathVariable("itemId") Long itemId) {
        cartService.itemOperation(itemId, CartService.ItemOperation.INCREASE);
        return "redirect:/order/cart";
    }

    @GetMapping("/decrease/{itemId}")
    public String decreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.itemOperation(itemId, CartService.ItemOperation.DECREASE);
        return "redirect:/order/cart";
    }

    @GetMapping("/remove/{itemId}")
    public String removeAllOfItem(@PathVariable("itemId") Long itemId) {
        cartService.itemOperation(itemId, CartService.ItemOperation.REMOVE);
        return "redirect:/order/cart";
    }

    @GetMapping("/summary")
    public String showSummary(Model model) {
        model.addAttribute("cart", cartService.cart);
        model.addAttribute("orderDto", new OrderDto());
        return "summary";
    }

    @PostMapping("/saveorder")
    public String saveorder(@ModelAttribute OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "redirect:/";
    }
}