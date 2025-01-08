
package org.lesson3.converter.app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("====================INFO===================");
        System.out.println("|   Converter App.                        |");
        System.out.println("|  App for converting Celsius to Fahrenheit.|");
        System.out.println("|   Version 1.0.                          |");
        System.out.println("===========================================");

        Scanner sc = new Scanner(System.in);
        int num;
        do {
            System.out.println("\n\nWhat do you want to convert?\n" +
                    "1. Convert Celsius To Fahrenheit\n" +
                    "2. Convert Fahrenheit To Celsius\n" +
                    "3. Enter 3 to exit.");
            num = sc.nextInt();
            switch (num){
                case 1 : {
                    System.out.println("Enter temperature in Celsius:");
                    double celsius = sc.nextDouble();
                    System.out.println("Result: " + convertCelsiusToFahrenheit(celsius));
                    break;
                }
                case 2 : {
                    System.out.println("Enter temperature in Fahrenheit:");
                    double fahrenheit = sc.nextDouble();
                    System.out.println("Result: " + convertFahrenheitToCelsius(fahrenheit));
                    break;
                }
                case 3 :
                    System.out.println("Ending...");
                    break;
                default :
                    System.out.println("Wrong answer! Try again!");
            }
        } while (num != 3);
    }

    private static double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private static double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
}
