package com.guilhermerizzatto.virtualstore.dao;

import java.util.Map;

import com.guilhermerizzatto.virtualstore.entities.Product;

public interface StockDao {

    public Map<Product, Integer> showProductsAndQuantity();
    public void insertProductAndQuantity(Product obj, Integer quantity);
    public void deleteProductAndQuantity(Long id);
    public void updateQuantity(Long id, Integer quantity);

}
