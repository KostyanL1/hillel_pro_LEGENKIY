package org.lesson2.converter.app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("====================INFO===================");
        System.out.println("|   Converter App.                        |");
        System.out.println("|  App for converting miles to kilometers.|");
        System.out.println("|   Version 1.0.                          |");
        System.out.println("===========================================");



        Scanner sc = new  Scanner(System.in);
        int num;
        do {
            System.out.println("\n\nWhat do you want to convert?\n" +
                    "1. Convert Mils To Kilometers\n" +
                    "2. Convert Kilometers To Mils\n" +
                    "3. Enter 3 to exit.");
            num = sc.nextInt();
            switch (num){
                case 1 : {
                    System.out.println("Enter number of mils:");
                    double mils = sc.nextDouble();
                    System.out.println("Result: " + convertMilsToKilometers(mils));
                    break;
                }
                case 2 : {
                    System.out.println("Enter number of kilometers:");
                    double kilometers = sc.nextDouble();
                    System.out.println("Result : " + convertKilometersToMils(kilometers));
                    break;
                }
                case 3 :
                    System.out.println("Ending...");
                    break;
                default :
                    System.out.println("Wrong answer! Try again!");
            }
        }while (num != 3);

    }



    private static double convertMilsToKilometers(double mils) {
        return mils * 1.60934;
    }

    private static double convertKilometersToMils(double kilometers) {

        return kilometers / 1.60934;
    }
}
