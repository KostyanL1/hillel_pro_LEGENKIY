package org.lesson38.demo;

import org.lesson38.model.Cart;
import org.lesson38.model.Product;
import org.lesson38.service.ProductRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.lesson38");
        Cart cart = context.getBean(Cart.class);
        ProductRepositoryImpl productRepositoryImpl = context.getBean(ProductRepositoryImpl.class);
        System.out.println("Корзину створено!");
        Scanner scanner = new Scanner(System.in);
        getMenu();

        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Корзина: \n" + cart + "\n");
                    getMenu();
                    break;
                case 2:
                    System.out.println("Додавання продукту...\nОберіть номер продукту для додавання до кошика: \n");
                    List<Product> allProducts = productRepositoryImpl.findAll();
                    if (allProducts.isEmpty()) {
                        System.out.println("Немає доступних продуктів.");
                        getMenu();
                        break;
                    }
                    for (int i = 1; i <= allProducts.size(); i++) {
                        System.out.println(i + ": " + allProducts.get(i - 1));
                    }
                    int addNumber = scanner.nextInt();
                    if (addNumber < 1 || addNumber > allProducts.size()) {
                        System.out.println("Неправильний номер продукту. Спробуйте ще раз.");
                    } else {
                        cart.getProducts().add(allProducts.get(addNumber - 1));
                        System.out.println("Продукт додано.");
                    }
                    getMenu();
                    break;

                case 3:
                    System.out.println("Видалення продукту...\nОберіть номер продукту для видалення з кошика: \n");
                    List<Product> products = cart.getProducts();
                    if (products.isEmpty()) {
                        System.out.println("Корзина порожня. Немає продуктів для видалення.");
                        getMenu();
                        break;
                    }
                    for (int i = 1; i <= products.size(); i++) {
                        System.out.println(i + ": " + products.get(i - 1));
                    }
                    int removeNumber = scanner.nextInt();
                    if (removeNumber < 1 || removeNumber > products.size()) {
                        System.out.println("Неправильний номер продукту. Спробуйте ще раз.");
                    } else {
                        products.remove(removeNumber - 1);
                        System.out.println("Продукт видалено.");
                    }
                    getMenu();
                    break;

                case 4:
                    cart = context.getBean(Cart.class);
                    System.out.println("Нова корзина створена!");
                    getMenu();
                    break;

                case 0:
                    System.out.println("Програма завершена... Вихід.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
                    getMenu();
            }
        }
    }

    public static void getMenu() {
        System.out.println(
                "\nМеню (Введіть номер пункту): \n" +
                        "1: Переглянути корзину.\n" +
                        "2: Додати продукт.\n" +
                        "3: Видалити продукт.\n" +
                        "4: Створити нову корзину.\n" +
                        "0: Завершити програму.\n"
        );
    }
}