package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ShoppingCart shoppingCart;
	private Product product;
	
	private Integer quantity;
	private BigDecimal price;
	
	public ProductItem() {
	}

	public ProductItem(ShoppingCart shoppingCart, Product product, Integer quantity) {
		super();
		this.shoppingCart = shoppingCart;
		this.product = product;
		this.quantity = quantity;
		this.price = subTotal();
	}
	
	public ProductItem(ProductItem obj) {
		super();
		this.shoppingCart = obj.getShoppingCart();
		this.product = obj.getProduct();
		this.quantity = obj.getQuantity();
		this.price = subTotal();
	}
		
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductItem [shoppingCart=" + shoppingCart + ", product=" + product + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	public BigDecimal subTotal() {
		return product.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
	

	
}
