package com.guilhermerizzatto.virtualstore.dtos.shoppingCart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.guilhermerizzatto.virtualstore.dtos.address.AddressDTOforShoppingCart;
import com.guilhermerizzatto.virtualstore.dtos.customer.CustomerDTOforShoppingCart;
import com.guilhermerizzatto.virtualstore.dtos.productItem.ProductItemDTOforShoppingCart;
import com.guilhermerizzatto.virtualstore.entities.Order;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

public class ShoppingCartDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal shippingPrice;
	
	private AddressDTOforShoppingCart address;
	private CustomerDTOforShoppingCart customer;
	private List<ProductItemDTOforShoppingCart> products = new ArrayList<>();
	private Order order;
	
	public ShoppingCartDTO(ShoppingCart obj) {
		super();
		this.id = obj.getId();
		this.shippingPrice = obj.getShippingPrice();
		this.address = new AddressDTOforShoppingCart(obj.getAddress());
		this.customer = new CustomerDTOforShoppingCart(obj.getCustomer());
		this.products = ProductItemDTOforShoppingCart.createListDTO(obj.getProducts());
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

	public AddressDTOforShoppingCart getAddress() {
		return address;
	}

	public void setAddress(AddressDTOforShoppingCart address) {
		this.address = address;
	}

	public CustomerDTOforShoppingCart getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTOforShoppingCart customer) {
		this.customer = customer;
	}

	public List<ProductItemDTOforShoppingCart> getProducts() {
		return products;
	}

	public void setProducts(List<ProductItemDTOforShoppingCart> products) {
		this.products = products;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "shoppingCartDTO [id=" + id + ", shippingPrice=" + shippingPrice + ", address=" + address + ", customer="
				+ customer + ", products=" + products + ", order=" + order + "]";
	}
	
	
	
	

}
