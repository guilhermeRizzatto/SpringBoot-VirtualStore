package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Product;

import java.util.Map;

public interface StockDao {

    public Map<Product, Integer> showProductsAndQuantity();
    public void insertProductAndQuantity(Product obj, Integer quantity);

    public void deleteProductAndQuantity(Long id);

}
