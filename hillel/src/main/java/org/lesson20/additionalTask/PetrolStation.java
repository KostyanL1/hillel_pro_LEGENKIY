package org.lesson20.additionalTask;


import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class PetrolStation{
    private static double amount;
    static BlockingQueue<Operator> operators = new LinkedBlockingQueue<>();
    static BlockingQueue<Car> cars = new LinkedBlockingQueue<>();

    public PetrolStation (){
        amount = 1000;
    }

    public static synchronized void doRefuel(double fuel)  {
        amount = amount - fuel;
        System.out.println("Залишилось " + amount);
    }

    public void initialOperators(){
        for (int i = 0; i < 3; i++) {
            operators.add(new Operator());
        }
    }
    public void initialCars(){
        for (int i = 1; i < 10; i++) {
            cars.add(new Car(i));
        }
    }

}
