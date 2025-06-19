package org.legenkiy.lesson40.service;

import org.legenkiy.lesson40.model.Order;
import org.legenkiy.lesson40.model.Product;
import org.legenkiy.lesson40.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepositoryImpl implements OrderRepository {

    private List<Order> orders;
    private int indexOrder = 1;
    private int indexProduct = 1;

    {
        this.orders = new ArrayList<>();
        Order order = new Order();
        List<Product> products = List.of(
                new Product(putIndexOfProduct(),"Cola", 250.00), new Product(putIndexOfProduct(), "Pizza", 300.00), new Product(putIndexOfProduct(),"Pepsi", 350.00)
        );
        order.setId(putIndexOfOrder());
        order.setProducts(products);
        order.setTotalCost(products.stream().mapToDouble(Product::getCost).sum());
        order.setCreationDate(LocalDateTime.now());
        orders.add(order);
    }



    @Override
    public Order findOrderById(int id) {
        return orders.stream().filter(i -> i .getId() == id).findFirst().orElseThrow();
    }

    @Override
    public List<Order> findAll() {
        return this.orders;
    }

    @Override
    public void save(Order order) {
        this.orders.add(order);
    }









    public int putIndexOfProduct(){
        int index = this.indexProduct;
        indexProduct++;
        return index;
    }

    public int putIndexOfOrder(){
        int index = this.indexOrder;
        indexOrder++;
        return index;
    }
}
