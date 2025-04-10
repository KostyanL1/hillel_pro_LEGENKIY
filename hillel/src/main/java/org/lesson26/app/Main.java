package org.lesson26.app;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("city Zhytomir");
        User user = new User("Kostya", "Legenkiy");
        user.setAddress(address);
        System.out.println(user.getName() + " " + user.getSurname() + " : " + user.getAddress().getAddress());
    }



}
