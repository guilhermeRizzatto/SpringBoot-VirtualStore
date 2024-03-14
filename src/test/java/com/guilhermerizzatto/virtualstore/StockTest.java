package com.guilhermerizzatto.virtualstore;

import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class StockTest {

	
	
	@Test //check if using a map to store it works properly
	void checkingIfWorks() {
		
		Product product = new Product(Long.valueOf(1), "Iphone 15", "DESCRIPTION", new BigDecimal(7499.99), "IMAGEURL");
		Stock stock = new Stock();
		
		stock.addProductAndQuantity(product, 5);
		
		Assertions.assertTrue(stock.getMap().containsKey(product));
	}
	
	@Test //check if have 2 products with the same ID, the map will store only 1 product
	void checkingTwoProductWithSameID() {
		
		Product product = new Product(Long.valueOf(1), "Iphone 15", "DESCRIPTION", new BigDecimal(7499.99), "IMAGEURL");
		Product product2 = new Product(Long.valueOf(1), "Iphone 14", "TEST,TEST,TEST", new BigDecimal(8745.99), "IMAGEURL");
		Stock stock = new Stock();
		
		stock.addProductAndQuantity(product, 5);
		stock.addProductAndQuantity(product2, 10);
		//because the product2 has the same id, the key in the map doesn´t change but the quantity changes
		
		
		int keys = 0;
		
		for(Product key : stock.getMap().keySet()) {
			keys++;
		}
		
		Assertions.assertEquals(1, keys);
	}

}
