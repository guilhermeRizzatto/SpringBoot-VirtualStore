package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Stock implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Map<Product ,Integer> productsWithQuantity = new HashMap<>();
	
	public Stock() {
	}

	public Map<Product, Integer> getMap() {
		return productsWithQuantity;
	}

	public void addProductAndQuantity(Product product, Integer quantity) {
		productsWithQuantity.put(product, quantity);
	}
	
	

}
