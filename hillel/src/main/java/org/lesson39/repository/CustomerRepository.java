package org.lesson39.repository;


import org.lesson39.model.Customer;

import javax.crypto.Cipher;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);
    Customer findById(int id);
    void update(int id, Customer customer);
    void delete(int id);
    List<Customer> findAll();

}
