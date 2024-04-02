package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

public interface ShoppingCartDao {
	
	public ShoppingCart findByCustomerId(Long id);
    public ShoppingCart insert(ShoppingCart obj);
    public void updateShippingPriceWithCep(Long id, String cep);
    public void delete(Long id);

}
