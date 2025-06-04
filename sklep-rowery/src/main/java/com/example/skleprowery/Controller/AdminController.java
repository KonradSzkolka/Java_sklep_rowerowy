package com.example.skleprowery.Controller;

import com.example.skleprowery.Model.Item;
import com.example.skleprowery.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;

    @Autowired
    public AdminController(ItemRepository itemRepository) {
        this.itemRepository=itemRepository;
    }
    @GetMapping
    private String adminPage(){
        return "adminview/addItem";
    }
    @PostMapping
    private String addItem(Item item) {
        //HomeController.items.add(Item);
        itemRepository.save(item);
        return "redirect:/";
    }

}
