package com.guilhermerizzatto.virtualstore.entities;

import java.math.BigDecimal;

public class ProductItem {
	
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

	@Override
	public String toString() {
		return "ProductItem [shoppingCart=" + shoppingCart + ", product=" + product + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	public BigDecimal subTotal() {
		return product.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
	
	

}
