package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.dao.implementation.ProductDaoImpl;
import com.guilhermerizzatto.virtualstore.dao.implementation.StockDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    ProductDaoImpl productImpl = new ProductDaoImpl();
    StockDaoImpl stockImpl = new StockDaoImpl();


    @GetMapping(value = "/findByID/{id}")
    public ResponseEntity<Product> findByID(@PathVariable Long id)  {
        Product result = productImpl.findById(id);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Product>> findAll()  {
        List<Product> list= productImpl.findAll();
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping(value = "/put")
    public ResponseEntity<String> update(@RequestBody Product obj){
        productImpl.update(obj);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }





}
