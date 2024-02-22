package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer quantity;
	
	private List<Product> products = new ArrayList<>();

	
	public Stock() {
	}
	
	public Stock(Integer quantity) {
		super();
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	

	@Override
	public String toString() {
		return "Stock [quantity=" + quantity + ", products=" + products + "]";
	}
	
	
	
	

}
