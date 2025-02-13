package org.lesson13.additionalTask;

import lombok.Data;

@Data
public class Order {
    private String nameClient;
    private Cocktail cocktail;

    public Order(String nameClient, Cocktail cocktail) {
        this.nameClient = nameClient;
        this.cocktail = cocktail;
    }
}
