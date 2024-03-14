package com.guilhermerizzatto.virtualstore;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;
import com.guilhermerizzatto.virtualstore.utils.ShippingPriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

class ShippingPriceCalculatorTest {

	@Test //check if the ShippingPriceCalculator.calc is returning the correct value when the shopping cart have 2 products
	void checkTheShippingPriceWith2Products() throws ApiException, InterruptedException, IOException {
		DecimalFormat df = new DecimalFormat("#.00");
		
		Address customerAddress = new Address(); //Random address, only for the test
		customerAddress.setId(Long.valueOf(1));
		customerAddress.setStreet("Rua Margaret Ober, 34");
		customerAddress.setDistrict("Jardim Bonifacio");
		customerAddress.setCity("São Paulo");
		customerAddress.setCity("SP");
		
		
		Product product = new Product(Long.valueOf(1), "Mousepad", "DESCRIPTION", new BigDecimal(19.99), "IMAGEURL");
		Product product2 = new Product(Long.valueOf(2), "Keyboard", "DESCRIPTION", new BigDecimal(29.99), "IMAGEURL");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		
		shoppingCart.setId(Long.valueOf(1));
		
		ProductItem mousepad = new ProductItem(shoppingCart, product, 1);
		ProductItem keyboard = new ProductItem(shoppingCart, product2, 1);
		
		BigDecimal totalPriceOfProductItem = mousepad.getPrice().add(keyboard.getPrice());
		Integer quantity = mousepad.getQuantity()+keyboard.getQuantity();
		
		
		BigDecimal shippingPrice = ShippingPriceCalculator.calc(totalPriceOfProductItem, GoogleDirectionsAPI.getDistance(customerAddress), quantity);
		
		Assertions.assertEquals(df.format(new BigDecimal(9.69)), df.format(shippingPrice));
	}

}
