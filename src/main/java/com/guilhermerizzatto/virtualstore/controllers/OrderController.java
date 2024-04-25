package com.guilhermerizzatto.virtualstore.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.OrderDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.order.OrderDTO;
import com.guilhermerizzatto.virtualstore.entities.Order;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    OrderDaoImpl orderImpl = new OrderDaoImpl();

    @GetMapping(value = "/findByCustomerID/{id}")
    public ResponseEntity<OrderDTO> findByCustomerID(@PathVariable Long id)  {
        OrderDTO result = new OrderDTO(orderImpl.findByCustomerID(id));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> post(@RequestBody Order obj)  {
        OrderDTO result = new OrderDTO(orderImpl.insert(obj));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
