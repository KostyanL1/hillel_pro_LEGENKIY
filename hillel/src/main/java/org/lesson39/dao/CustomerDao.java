package org.lesson39.dao;

import lombok.RequiredArgsConstructor;
import org.lesson21.app.Author;
import org.lesson39.model.Customer;
import org.lesson39.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerDao implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;



    @Override
    public void save(Customer customer) {
        jdbcTemplate.update("INSERT INTO customer (full_name, email, social_security_number) VALUES (?, ?, ?)", customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber());
    }

    @Override
    public Customer findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id=?",  new BeanPropertyRowMapper<>(Customer.class), id);
    }

    @Override
    public void update(int id, Customer customer) {
        jdbcTemplate.update("UPDATE customer SET full_name=?, email=?, social_security_number=? WHERE id=?", customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM customer WHERE id=?", id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SElECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
    }
}
