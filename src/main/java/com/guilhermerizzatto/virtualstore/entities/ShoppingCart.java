package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal shippingPrice;
	
	private List<ProductItem> products = new ArrayList<>();
	
	public ShoppingCart() {
	}

	public ShoppingCart(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(BigDecimal shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	
	public List<ProductItem> getProducts() {
		return products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", shippingPrice=" + shippingPrice + "]";
	}
	
	
	
	

}
