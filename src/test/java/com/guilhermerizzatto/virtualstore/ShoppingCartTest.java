package com.guilhermerizzatto.virtualstore;

import com.guilhermerizzatto.virtualstore.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

class ShoppingCartTest {

	@Test //verify the method shippingPriceCalculator in the class ShoppingCart
	void verifyShippingPriceCalculator() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		Customer customer = new Customer();
		customer.setId(Long.valueOf(1));
		
		Address customerAddress = new Address(); //Random address, only for the test
		customerAddress.setId(Long.valueOf(1));
		customerAddress.setStreet("Rua Margaret Ober, 34");
		customerAddress.setDistrict("Jardim Bonifacio");
		customerAddress.setCity("São Paulo");
		customerAddress.setCity("SP");
		customerAddress.setCustomer(customer);
		
		customer.getAdresses().add(customerAddress);
		
		Product product = new Product(Long.valueOf(1), "Mousepad", "DESCRIPTION", new BigDecimal(19.99), "IMAGEURL");
		Product product2 = new Product(Long.valueOf(2), "Keyboard", "DESCRIPTION", new BigDecimal(29.99), "IMAGEURL");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(Long.valueOf(1));
		shoppingCart.setCustomer(customer);
		shoppingCart.setAddress(customer.getAdresses().get(0));
		
		ProductItem mousepad = new ProductItem(shoppingCart, product, 4);
		ProductItem keyboard = new ProductItem(shoppingCart, product2, 9);
		
		shoppingCart.getProducts().add(mousepad);
		shoppingCart.getProducts().add(keyboard);
		
		shoppingCart.shippingPriceCalculator();
		
		Assertions.assertEquals(df.format(new BigDecimal(13.59)), df.format(shoppingCart.getShippingPrice()));		
	}

}
