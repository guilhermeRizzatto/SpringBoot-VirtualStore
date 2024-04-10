package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartDao {
	
	public ShoppingCart findByCustomerId(Long id);
    public ShoppingCart insert(ShoppingCart obj);


    public BigDecimal showShippingPriceWithCep(Long id, String cep); //Only use in front-end, to calculate shippingPrice in shopping cart page (Visual Only)
    public void updateAddressInShoppingCart(Long id, Address address);
    public void delete(Long id);

}
