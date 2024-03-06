package com.guilhermerizzatto.virtualstore.entities;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.utils.ShippingPriceCalculator;

public class ShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal shippingPrice;
	
	
	private Address address;
	private Customer customer;
	private List<ProductItem> products = new ArrayList<>();
	private Order order;
	
	public ShoppingCart() {
	}

	public ShoppingCart(Long id, Address address, Customer customer){
		super();
		this.id = id;
		this.address = address;
		this.customer = customer;
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
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
	
	public void shippingPriceCalculator() {
		BigDecimal priceOfProducts = new BigDecimal(0);
		int quantity = 0;
		Long distance = Long.valueOf(0);
		
		try {
			
		distance = GoogleDirectionsAPI.getDistance(address);
		
		}catch (ApiException e) {
			System.out.println(e.getMessage());
		}catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		for(ProductItem x : products) {
			quantity++;
			priceOfProducts = priceOfProducts.add(x.getPrice()); 
		}
		
		shippingPrice = ShippingPriceCalculator.calc(priceOfProducts, distance, quantity);
		
	}
	
	
	
	
	

}
