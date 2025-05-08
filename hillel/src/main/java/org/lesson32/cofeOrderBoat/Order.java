package org.lesson32.cofeOrderBoat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
    private int id;
    private String customer;

    public Order(int id, String newCustomer){
        this.customer = newCustomer;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Number | Name\n"+
                id + " | " + customer;
    }
}
