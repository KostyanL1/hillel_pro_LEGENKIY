package org.lesson38.service;

import lombok.Getter;
import org.lesson38.model.Product;
import org.lesson38.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Getter
public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products;

    {
        this.products = new ArrayList<>();
        products.add(new Product(1, "Cola", 250.00));
        products.add(new Product(2, "Chips", 150.00));
        products.add(new Product(3, "Chocolate", 200.00));
        products.add(new Product(4, "Juice", 180.00));
        products.add(new Product(5, "Cookies", 220.00));
        products.add(new Product(6, "Milk", 170.00));
        products.add(new Product(7, "Bread", 100.00));
        products.add(new Product(8, "Cheese", 350.00));
        products.add(new Product(9, "Yogurt", 190.00));
        products.add(new Product(10, "Coffee", 300.00));
    }

    @Override
    public List<Product> findAll() {
        return this.products;
    }

    @Override
    public void save(Product product) {
        this.products.add(product);
    }

    @Override
    public void update(int id, Product product) {

        Optional<Product> optionalProduct = this.products.stream().filter(i -> i.getId() == id).findFirst();
        if (optionalProduct.isPresent()){

            Product product1 = optionalProduct.get();
            products.remove(optionalProduct);
            products.add(product);
            System.out.println("Продукт оновлено!");

        }else {
            System.out.println("Продукт не знайдено!");
        }
    }

    @Override
    public void delete(int id) {
        Optional<Product> optionalProduct = this.products.stream().filter(i -> i.getId() == id).findFirst();
        if (optionalProduct.isPresent()) {
            products.remove(optionalProduct.get());
            System.out.println("Продукт видалено!");
        }else {
            System.out.println("Видалити неможливо. Продукт не знайдено.");
        }
    }
}
