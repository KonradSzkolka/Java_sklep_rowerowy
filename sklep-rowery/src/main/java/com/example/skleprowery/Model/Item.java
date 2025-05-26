package com.example.skleprowery.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;



@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String imgUrl;
    private BigDecimal price;
    private String name;

    public Item(String imgURL, BigDecimal price, String name) {
        this.imgUrl = imgURL;
        this.price = price;
        this.name = name;
    }
}
