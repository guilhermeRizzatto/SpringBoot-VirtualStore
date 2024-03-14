package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.dao.implementation.EmployeeDaoImpl;
import com.guilhermerizzatto.virtualstore.dao.implementation.ProductDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Employee;
import com.guilhermerizzatto.virtualstore.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    ProductDaoImpl jdbcImpl = new ProductDaoImpl();


    @GetMapping(value = "/findByID/{id}")
    public ResponseEntity<Product> findByID(@PathVariable Long id)  {
        Product result = jdbcImpl.findById(id);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product obj)  {
        Product result = jdbcImpl.insert(obj);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
