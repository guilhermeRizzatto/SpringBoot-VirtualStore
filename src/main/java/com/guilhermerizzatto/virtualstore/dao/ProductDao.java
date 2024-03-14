package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Product;

import java.util.List;

public interface ProductDao {

    public Product findById(Long id);
    public List<Product> findAll();
    public Product insert(Product obj);
    public void update(Product product);
    public void delete(Long id);

}
