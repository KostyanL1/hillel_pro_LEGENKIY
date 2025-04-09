package org.lesson25.app;

public class Car implements Transport{
    @Override
    public void move() {
        System.out.println("Car moving!");
    }
}
