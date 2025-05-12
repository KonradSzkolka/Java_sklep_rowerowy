package com.example.skleprowery;

import com.example.skleprowery.Model.Item;
import com.example.skleprowery.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {
    private final ItemRepository itemRepository;

    @Autowired
    public DbInit(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;

    }
    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item("https://i.pinimg.com/originals/bc/2b/dd/bc2bdd2d0eb20fa5668a75c778b1bfba.gif",new BigDecimal("200.20"),"Rower"),
        new Item("https://marketrowerowy.pl/images/5.1.png",new BigDecimal("1000.50"),"Goral"),
        new Item("https://dostawanajutro.pl/environment/cache/images/0_0_productGfx_15269/rower-MIFA-3-biegi_mietowy_4a.jpg",new BigDecimal("100.00"),"Skladak")
                ));
    }
}
