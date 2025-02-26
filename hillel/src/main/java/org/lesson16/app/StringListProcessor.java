package org.lesson16.app;

public class StringListProcessor {

    public static  int countUppercase(String s){
        char[] array = s.toCharArray();
        int count = 0;
        for (char c : array) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }
}
