package com.guilhermerizzatto.virtualstore;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;
import com.guilhermerizzatto.virtualstore.utils.ShippingPriceCalculator;

class ShippingPriceCalculatorTest {

	@Test
	void checkTheShippingPriceForShoppingCart() throws ApiException, InterruptedException, IOException {
		DecimalFormat df = new DecimalFormat("#.00");
		
		Address customerAddress = new Address(); //Random address, only for the test
		customerAddress.setId(Long.valueOf(1));
		customerAddress.setStreet("Rua Margaret Ober, 34");
		customerAddress.setDistrict("Jardim Bonifacio");
		customerAddress.setCity("São Paulo");
		customerAddress.setCity("SP");
		
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setAddress(customerAddress);
				
		BigDecimal shippingPrice = ShippingPriceCalculator.calcForShoppingCart(GoogleDirectionsAPI.getDistance(shoppingCart.getAddress()));
		
		Assertions.assertEquals(df.format(new BigDecimal(8.91)), df.format(shippingPrice));
	}
	
	@Test
	void checkTheShippingPriceForOrder() {
		DecimalFormat df = new DecimalFormat("#.00");
			
		Product product = new Product(Long.valueOf(1), "Mousepad", "DESCRIPTION", new BigDecimal(19.99), "IMAGEURL");
		Product product2 = new Product(Long.valueOf(2), "Keyboard", "DESCRIPTION", new BigDecimal(29.99), "IMAGEURL");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(Long.valueOf(1));
		
		ProductItem mousepad = new ProductItem(shoppingCart, product, 4);
		ProductItem keyboard = new ProductItem(shoppingCart, product2, 9);
		
		shoppingCart.getProducts().add(mousepad); 
		shoppingCart.getProducts().add(keyboard);
		
		
		Assertions.assertEquals(df.format(new BigDecimal(4.68)), df.format(ShippingPriceCalculator.calcForOrder(new BigDecimal(349.87), 13)));		
	}

}
