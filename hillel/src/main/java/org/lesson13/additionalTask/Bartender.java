package org.lesson13.additionalTask;


import lombok.Data;

@Data
public class Bartender implements Runnable{

    private final Bar bar;

    @Override
    public void run() {
        bar.makeCocktail();
    }
}
