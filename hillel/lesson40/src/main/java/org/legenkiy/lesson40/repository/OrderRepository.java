package org.legenkiy.lesson40.repository;

import org.legenkiy.lesson40.model.Order;

import java.util.List;

public interface OrderRepository {

    Order findOrderById(int id);
    List<Order> findAll();
    void save(Order order);

}
