package org.legenkiy.lesson41.repository;

import org.legenkiy.lesson41.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findOrderById(int id);

    List<Order> findAll();

    void save(Order order);

    boolean deleteById(int id);

    boolean update(Order order);
}
