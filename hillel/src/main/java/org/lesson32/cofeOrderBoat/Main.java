package org.lesson32.cofeOrderBoat;

import org.lesson24.app.Logger;

public class Main {

    public static void main(String[] args) {

        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        coffeeOrderBoard.createOrders(70);

        coffeeOrderBoard.deliver();

        coffeeOrderBoard.deliver(3);

        coffeeOrderBoard.draw();

    }
}
