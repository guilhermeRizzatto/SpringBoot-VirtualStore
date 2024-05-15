package com.guilhermerizzatto.virtualstore.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.ProductDaoImpl;
import com.guilhermerizzatto.virtualstore.dao.implementation.StockDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Product;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    StockDaoImpl stockImpl = new StockDaoImpl();
    ProductDaoImpl productImpl = new ProductDaoImpl();


    @GetMapping(value = "/showProductsAndQuantity")
    public ResponseEntity<Map<Product,Integer>> showProductsAndQuantity()  {
        return ResponseEntity.status(HttpStatus.OK).body(stockImpl.showProductsAndQuantity());
    }

    @PostMapping(value = "/post/quantity={quantity}")
    public ResponseEntity<Product> post(@RequestBody Product obj, @PathVariable Integer quantity)  {
        Product result = productImpl.insert(obj);
        stockImpl.insertProductAndQuantity(result, quantity);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @PutMapping(value = "/put/ID={productID}/quantity={quantity}")
    public ResponseEntity<String> update(@PathVariable Long productId, @PathVariable Integer quantity){
    	stockImpl.updateQuantity(productId,quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }

    @DeleteMapping(value = "/delete/ID={id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        stockImpl.deleteProductAndQuantity(id);
        productImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
}
