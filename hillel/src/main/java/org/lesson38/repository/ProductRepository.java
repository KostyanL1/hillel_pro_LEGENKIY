package org.lesson38.repository;

import org.lesson38.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    void save(Product product);
    void update(int id, Product product);
    void delete(int id);

}
