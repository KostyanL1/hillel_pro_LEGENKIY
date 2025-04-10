package org.lesson25.app;

public class Client {

    public void run(){
        CarFactory carFactory = new CarFactory();
        carFactory.createTransport().move();
        PlaneFactory planeFactory = new PlaneFactory();
        planeFactory.createTransport().move();
    }
}
