package com.guilhermerizzatto.virtualstore.dao;

import java.util.List;

import com.guilhermerizzatto.virtualstore.entities.ProductItem;

public interface ProductItemDao {
	
	public ProductItem findByProduct(Long id);
    public List<ProductItem> findAll();
    public ProductItem insert(ProductItem obj);
    public void update(ProductItem productItem);
    public void delete(Long id);

}
