package org.lesson16.app;

import java.time.LocalTime;
import java.util.Locale;

public class RandomNumberGenerator {
    public  static  int generateRandomNumber(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
