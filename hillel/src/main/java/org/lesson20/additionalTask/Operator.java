package org.lesson20.additionalTask;


import lombok.Setter;

@Setter
public class Operator implements Runnable{

    private Car car;

    @Override
    public void run() {
        System.out.println("Заправка почалась для автомобіля " + car.getNumberOfCar());
        try {
            Thread.sleep((long) (Math.random() * 7000) + 3000);
            PetrolStation.doRefuel(car.getNeedFuel());
            System.out.println("Автомобілю потірбно - " + car.getNeedFuel());
            PetrolStation.operators.add(this);
            System.out.println("Машина " + car.getNumberOfCar() + " заправлена!");

        }catch (InterruptedException e ){
            System.out.println(e.getMessage());
        }
    }
}
