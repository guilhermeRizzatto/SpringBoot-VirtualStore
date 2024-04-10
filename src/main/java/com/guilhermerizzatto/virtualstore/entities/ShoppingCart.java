package com.guilhermerizzatto.virtualstore.entities;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.utils.GetDistanceAPI;
import com.guilhermerizzatto.virtualstore.utils.ShippingPriceCalculator;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public ShoppingCart(Long id, Address address, Customer customer, Order order){
		super();
		this.id = id;
		this.address = address;
		this.customer = customer;
		this.order = order;
	}
	
	public ShoppingCart(ShoppingCart obj){
		super();
		this.id = obj.getId();
		this.address = obj.getAddress();
		this.customer = obj.getCustomer();
		this.order = obj.getOrder();
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
		Long distance = GetDistanceAPI.getDistanceWithAddress(address);
		shippingPrice = ShippingPriceCalculator.calcForShoppingCart(distance);
	}

	public static BigDecimal getTheShippingPriceWithCEP(String cep){
		Long distance = GetDistanceAPI.getDistanceWithCEP(cep);
		return ShippingPriceCalculator.calcForShoppingCart(distance);
	}

	
	
	
	
	

}
