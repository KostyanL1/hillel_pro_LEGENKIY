package org.lesson38.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Scope("prototype")
public class Cart {

    private int id;
    private List<Product> products = new ArrayList<>();
    private double total;


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", total=" + String.format("%.2f", total) + " UAH" +
                '}';
    }

}
