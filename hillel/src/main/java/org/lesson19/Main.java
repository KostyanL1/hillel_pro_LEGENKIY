package org.lesson19;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29,
                31, 33, 35, 37, 39, 41, 43, 45, 47, 49};

        int[] unsortedArray = {23, 1, 45, 9, 3, 17, 33, 5, 49, 7, 29, 21, 35, 15, 43,
                39, 13, 19, 47, 31, 41, 27, 25, 37, 11};


        Arrays.stream(ArrayUtils.mergeSort(unsortedArray)).forEach(System.out::println);

        int target = 27;
        int index = ArrayUtils.binarySearch(sortedArray, target);
        if (index != -1) {
            System.out.println("Елемент " + target + " знайдено на позиції: " + index);
        } else {
            System.out.println("Елемент не знайдено.");
        }
    }
    }

