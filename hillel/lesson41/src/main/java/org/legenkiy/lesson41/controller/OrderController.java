package org.legenkiy.lesson41.controller;

import org.legenkiy.lesson41.model.Order;
import org.legenkiy.lesson41.service.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepositoryImpl orderRepository;

    @Autowired
    public OrderController(OrderRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                Map.of("status", "ok", "orders", orderRepository.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        return orderRepository.findOrderById(id)
                .map(order -> ResponseEntity.ok(
                        Map.of("status", "ok", "order", order)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("status", "not_found", "message", "Order not found")));
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNew(@RequestBody Order order) {
        orderRepository.save(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("status", "created"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        boolean deleted = orderRepository.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("status", "deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "not_found", "message", "Order not found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Order order) {
        order.setId(id);
        boolean updated = orderRepository.update(order);
        if (updated) {
            return ResponseEntity.ok(Map.of("status", "updated"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "not_found", "message", "Order not found"));
        }
    }
}
