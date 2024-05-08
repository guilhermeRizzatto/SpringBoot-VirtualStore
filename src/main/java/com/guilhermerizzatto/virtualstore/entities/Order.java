package com.guilhermerizzatto.virtualstore.entities;

import com.guilhermerizzatto.virtualstore.utils.ShippingPriceCalculator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal total;
	private Instant moment;
	
	private ShoppingCart shoppingCart;
	
	public Order() {
	}
	
	public Order(Long id, Instant moment, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.moment = Instant.now();
		this.shoppingCart = shoppingCart;
		this.total = totalPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", moment=" + moment + "]";
	}
	
	public BigDecimal totalPrice() {
		BigDecimal total = new BigDecimal(0);
		int productQuantity = 0;
		
		for(ProductItem p : shoppingCart.getProducts()) {
			 total = total.add(p.getPrice());
			 productQuantity += p.getQuantity();
		}

		total = total.add(ShippingPriceCalculator.calcForOrder(total, productQuantity));
		return total.add(shoppingCart.getShippingPrice());
	}
	
	
	

}
