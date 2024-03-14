package com.guilhermerizzatto.virtualstore;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

class ProductItemTest {

	@Test
	//verify if the subTotal method is correctly implemented
	void subTotal() {
		//Uses decimalFormat because when it's returning this subTotal in response, it has to be returned with two numbers after decimal point
		DecimalFormat df = new DecimalFormat("#.00");
		
		Product product = new Product(Long.valueOf(1), "Iphone 15", "Iphone 15, 128gb 5G", new BigDecimal(7900.99), "IMAGEURL");
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(Long.valueOf(1));
		
		ProductItem productItem = new ProductItem(shoppingCart, product, 5);
		Assertions.assertEquals(df.format(39504.95),df.format(productItem.subTotal()));
	}
	
	

}
