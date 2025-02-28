package org.lesson17.app;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Main.java
public class Main {

        public static void main(String[] args) {
            List<Product> products = Arrays.asList(
                    new Product("Laptop", "Electronics", 1200.0),
                    new Product("Coffee Maker", "Appliances", 80.0),
                    new Product("Headphones", "Electronics", 150.0),
                    new Product("Blender", "Appliances", 50.0)
            );
            Map<String, Double> result = products.stream()
                    .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));


            System.out.println("Результат: " + result);
            System.out.println("Категорія з найвищим середнім значеням: " + result.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow());

        }
}
