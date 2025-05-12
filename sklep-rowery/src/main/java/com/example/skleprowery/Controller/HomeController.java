package com.example.skleprowery.Controller;

import com.example.skleprowery.Model.Item;
import com.example.skleprowery.repository.ItemRepository;
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

    @Autowired
    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        //List<Item> items = itemRepository.findAll();
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }
    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        Optional<Item> oItem = itemRepository.findById(itemId);
        oItem.ifPresent(cart::add);
        session.setAttribute("cart", cart);
        return "redirect:/";
    }
}
