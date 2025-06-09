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
//        itemRepository.saveAll(List.of(
//                new Item("GÓRSKI SZIMANOCHA", new BigDecimal(999.99), "https://i.makeagif.com/media/2-03-2024/K_N8Ps.gif"),
//                new Item("BMX", new BigDecimal(5500), "https://www.gifbin.com/bin/112010/1290708452_kid-bmx-half-pipe-fail.gif"),
//                new Item("SKŁADAK", new BigDecimal(99.99), "https://25.media.tumblr.com/36fc7e271582a4fe643a18d0da28724d/tumblr_n0fc5nB5Xy1t95h1uo1_400.gif")
//        ));
    }
}