package org.lesson9.additionalTask;

import java.util.NoSuchElementException;

public class Box<T extends Fruit> {

    private T fruit;
    private int countOfProducts;


    public Box(T fruit, int countOfProducts) {
        this.fruit =  fruit;
        this.countOfProducts = countOfProducts;
    }

    public T getFruit() {
        return fruit;
    }

    public int getCountOfProducts() {
        return countOfProducts;
    }

    public void addFruitToBox(T fruit){
        if (this.fruit.equals(fruit)){
            countOfProducts++;
        }else {
            throw new NoSuchElementException("Ця коробка не може містити цей фрукт!" + fruit.getClass().getName());
        }
    }

    public void addFruitsToBox(T[] fruits){
            int count = 0;
        for (T fruit : fruits) {
            if (!fruit.equals(this.fruit)) {
                throw new NoSuchElementException("Ця коробка не може містити цей фрукт!" + fruit.getClass().getName());
            } else {
                count++;
            }
        }
        this.countOfProducts += count;
    }

    public double getWeight(){
        if (fruit.getWeightOfOnePiece() * countOfProducts == 0){
            throw new NoSuchElementException("Коробка пуста!");
        }
        return fruit.getWeightOfOnePiece() * countOfProducts;
    }

    public boolean compare(Box<T> box) {
        return this.getWeight() == box.getWeight();
    }


    public void merge(Box box){
        if (box.getFruit().equals(this.fruit)){
            countOfProducts += box.getCountOfProducts();
        }
        else {
            throw new NoSuchElementException("В коробках  різні фрукти!");
        }
    }
}
