package org.lesson13.additionalTask;

import lombok.Data;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Data
public class Bar {
    private List<Cocktail> cocktails = List.of(
            new Cocktail("Mojito", 250),
            new Cocktail("Margarita", 200),
            new Cocktail("Pina Colada", 300),
            new Cocktail("Cosmopolitan", 150),
            new Cocktail("Martini", 180),
            new Cocktail("Daiquiri", 220),
            new Cocktail("Bloody Mary", 250),
            new Cocktail("Tequila Sunrise", 230),
            new Cocktail("Whiskey Sour", 200),
            new Cocktail("Long Island Iced Tea", 350),
            new Cocktail("Negroni", 180),
            new Cocktail("Caipirinha", 190),
            new Cocktail("Manhattan", 170),
            new Cocktail("Gin Tonic", 250),
            new Cocktail("Old Fashioned", 200)
    );

    public Bar(){
        for (int i = 0; i < 3; i++) {
            bartenders.submit(new Bartender(this));
        }
    }

    private final ExecutorService bartenders = Executors.newFixedThreadPool(3);
    private final BlockingDeque<Order> orders = new LinkedBlockingDeque<>();

    public synchronized void getOrder(Cocktail cocktail, String name) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Замовлення від клієнта " + name + " прийнято!");
        orders.add(new Order(name, cocktail));
        orderToBartender(name);
    }

    public synchronized void orderToBartender(String name) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Замовлення від клієнта " + name + " передано бармену");

    }

    public void makeCocktail(){
        while (true) {
            try {
                Order order = orders.take();
                System.out.println("Бармен готує " + order.getCocktail().getName() + " для " + order.getNameClient());
                Thread.sleep(10000);
                System.out.println("Бармен подав " + order.getCocktail().getName() + " для " + order.getNameClient());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
