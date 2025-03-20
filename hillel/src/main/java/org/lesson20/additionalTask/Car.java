package org.lesson20.additionalTask;


import lombok.Data;

@Data
public class Car {
    private int numberOfCar;
    private double needFuel;


    public Car(int numberOfCar) {
        this.numberOfCar = numberOfCar;
        this.needFuel = (Math.random() * 25) + 5;
    }



}
