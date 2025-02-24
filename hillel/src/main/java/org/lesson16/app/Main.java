package org.lesson16.app;

import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        MathOperation sum = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
        System.out.println(sum.operate(2, 5));


        StringManipulator upper = (String string) -> string.toUpperCase();
        String upperString = upper.toUpperCase("hello world");
        System.out.println(upperString);

        Function<String, Integer> function = StringListProcessor::countUppercase;
        int uppercaseCount = function.apply("HeLLo WoRLd");
        System.out.println(uppercaseCount);

        Supplier<Integer> supplier = () -> RandomNumberGenerator.generateRandomNumber(1, 100);
        System.out.println(supplier.get());


    }
}
