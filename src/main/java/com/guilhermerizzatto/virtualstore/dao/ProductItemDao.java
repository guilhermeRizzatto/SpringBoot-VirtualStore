package com.guilhermerizzatto.virtualstore.dao;

import java.util.List;

import com.guilhermerizzatto.virtualstore.entities.ProductItem;

public interface ProductItemDao {
	
	public List<ProductItem> findByShoppingCart(Long id);
    public ProductItem insert(ProductItem obj);
    public void update(ProductItem productItem);
    public void delete(ProductItem productItem);

}
