package org.lesson13.additionalTask;


import lombok.Data;

@Data
public class Cocktail {
    private String name;
    private int volume;

    public Cocktail(String name, int volume){
        this.name = name;
        this.volume = volume;
    }
}
