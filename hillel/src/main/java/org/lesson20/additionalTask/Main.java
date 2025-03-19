package org.lesson20.additionalTask;




public class Main {

    public static void main(String[] args) throws InterruptedException {
        PetrolStation petrolStation = new PetrolStation();
        petrolStation.initialCars();
        petrolStation.initialOperators();
        while (!PetrolStation.cars.isEmpty()){
            if (PetrolStation.operators.isEmpty()){
                System.out.println("Всі оператори зайняті");
            }
            Car car = PetrolStation.cars.take();
            System.out.println("Оператор доступний!");
            Operator operator = PetrolStation.operators.take();
            operator.setCar(car);
            Thread thread = new Thread(operator);
            thread.start();
        }
    }
}