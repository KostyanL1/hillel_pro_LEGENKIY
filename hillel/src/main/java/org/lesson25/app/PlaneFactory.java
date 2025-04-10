package org.lesson25.app;

public class PlaneFactory extends TransportFactory{
    @Override
    public Transport createTransport() {
        return new Plane();
    }
}
