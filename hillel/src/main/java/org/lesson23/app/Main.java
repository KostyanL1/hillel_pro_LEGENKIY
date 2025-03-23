package org.lesson23.app;

import org.legenkiy.PasswordGenerator;

public class Main {


    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        System.out.println(passwordGenerator.generatePassword(8));
        System.out.println("\n---------------");
        System.out.println(passwordGenerator.generatePassword(4));
        System.out.println("\n---------------");
        System.out.println(passwordGenerator.generatePassword(12));
        System.out.println("\n---------------");
    }

}
