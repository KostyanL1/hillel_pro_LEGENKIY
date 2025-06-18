package org.legenkiy.lesson40.controller;


import lombok.RequiredArgsConstructor;
import org.legenkiy.lesson40.model.Order;
import org.legenkiy.lesson40.service.OrderRepositoryImpl;
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


    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        Map.of(
                                "status", "ok",
                                "orders", orderRepository.findAll()
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        Map.of(
                                "status", "ok",
                                "order", orderRepository.findOrderById(id)
                        )
                );
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNew(@RequestBody Order order){
        orderRepository.save(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        Map.of(
                                "status", "created"
                        )
                );
    }



}
