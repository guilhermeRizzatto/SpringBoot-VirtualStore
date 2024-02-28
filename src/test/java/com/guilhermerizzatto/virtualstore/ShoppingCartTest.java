package com.guilhermerizzatto.virtualstore;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

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
		
		ShoppingCart shoppingCart = new ShoppingCart(Long.valueOf(1), customer.getAdresses().get(0), customer);
		
		ProductItem mousepad = new ProductItem(shoppingCart, product, 1);
		ProductItem keyboard = new ProductItem(shoppingCart, product2, 1);
		
		shoppingCart.getProducts().add(mousepad);
		shoppingCart.getProducts().add(keyboard);
		
		shoppingCart.shippingPriceCalculator();
		
		Assertions.assertEquals(df.format(new BigDecimal(9.69)), df.format(shoppingCart.getShippingPrice()));		
	}

}
