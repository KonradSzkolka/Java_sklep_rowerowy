package com.example.skleprowery.Model.order;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private long orderId;
    private Long itemId;
    private int amount;

    public OrderItem(int amount, Long itemId, long orderId) {
        this.amount = amount;
        this.itemId = itemId;
        this.orderId = orderId;
    }
}
