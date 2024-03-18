package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.dao.implementation.ProductDaoImpl;
import com.guilhermerizzatto.virtualstore.dao.implementation.StockDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    StockDaoImpl stockImpl = new StockDaoImpl();
    ProductDaoImpl productImpl = new ProductDaoImpl();


    @GetMapping(value = "/showProductsAndQuantity")
    public ResponseEntity<Map<Product,Integer>> showProductsAndQuantity()  {
        return ResponseEntity.status(HttpStatus.OK).body(stockImpl.showProductsAndQuantity());
    }

    @PostMapping(value = "/post/{quantity}")
    public ResponseEntity<Product> post(@RequestBody Product obj, @PathVariable Integer quantity)  {
        Product result = productImpl.insert(obj);
        stockImpl.insertProductAndQuantity(result, quantity);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        stockImpl.deleteProductAndQuantity(id);
        productImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
}
