package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Stock implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String local = "Rua Antônio Ovídio Rodrigues, 1 - Bomfim, Jundiaí - SP"; //fictional location
	

	private Map<Product ,Integer> productsWithQuantity = new HashMap<>();
	
	public Stock() {
	}

	public Map<Product, Integer> get() {
		return productsWithQuantity;
	}

	public void addProductAndQuantity(Product product, Integer quantity) {
		productsWithQuantity.put(product, quantity);
	}

	
	

}
