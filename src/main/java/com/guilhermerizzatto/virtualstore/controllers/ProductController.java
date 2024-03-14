package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.dao.implementation.ProductDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Product>> findAll()  {
        List<Product> list= jdbcImpl.findAll();
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product obj)  {
        Product result = jdbcImpl.insert(obj);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(value = "/put")
    public ResponseEntity<String> update(@RequestBody Product obj){
        jdbcImpl.update(obj);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        jdbcImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }




}
