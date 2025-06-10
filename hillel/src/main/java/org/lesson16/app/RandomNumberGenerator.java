package org.lesson16.app;

public class RandomNumberGenerator {
    public  static  int generateRandomNumber(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
