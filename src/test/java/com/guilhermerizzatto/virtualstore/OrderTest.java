package com.guilhermerizzatto.virtualstore;

import com.guilhermerizzatto.virtualstore.entities.Order;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;

class OrderTest {

	@Test
	void totalPriceIsCorrectly() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		Product product = new Product(Long.valueOf(1), "Iphone 15", "Iphone 15, 128gb 5G", new BigDecimal(7900.99), "IMAGEURL");
		Product product2 = new Product(Long.valueOf(2), "Iphone 15 PRO MAX", "Iphone 15 PRO MAX, 256gb 5G", new BigDecimal(11999.99), "IMAGEURL");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		
		shoppingCart.setId(Long.valueOf(1));
		shoppingCart.setShippingPrice(new BigDecimal(27.99));
		
		ProductItem productItem = new ProductItem(shoppingCart, product, 5);
		ProductItem productItem2 = new ProductItem(shoppingCart, product2, 10);
		
		shoppingCart.getProducts().add(productItem);
		shoppingCart.getProducts().add(productItem2);
		
		Order order = new Order(Long.valueOf(1), Instant.now(), shoppingCart);
		
		Assertions.assertEquals(df.format(159532.84), df.format(order.getTotal()));
	}

}


