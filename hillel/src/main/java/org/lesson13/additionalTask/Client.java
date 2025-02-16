package org.lesson13.additionalTask;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
public class Client implements Runnable{

    private final Bar bar;
    private final String name;

    public Client (String name, Bar bar){
        this.name = name;
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println("Клієнт " + name + " робить замовлення...");
            Thread.sleep(500);
            Cocktail cocktail = chooseCocktail(bar.getCocktails());
            System.out.println("Клієнт " + name + " зробив замовлення "+ cocktail.getName() );
            bar.getOrder(cocktail, this.name);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public Cocktail chooseCocktail(List<Cocktail> cocktails){
        int cocktail = (int) (Math.random() * cocktails.size());
        return cocktails.get(cocktail);
    }
}
