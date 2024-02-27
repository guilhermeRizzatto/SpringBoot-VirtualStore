package com.guilhermerizzatto.virtualstore.entities;

import java.math.BigDecimal;

public class ShippingPriceCalculator {
	
	private static final Double pricePerKm = 0.09;
	private static final Double priceEveryTwoProducts = 0.39;

	public static BigDecimal calc(BigDecimal priceOfProducts, Long distance, Integer quantityOfProducts) {
		BigDecimal priceForFreeShippingPrice = new BigDecimal(249.99);
		if(priceOfProducts.compareTo(priceForFreeShippingPrice) > 0) {
			return new BigDecimal(0);
		}
		//every 2 products in shoppingCart price increase 1.39
		if((quantityOfProducts % 2) != 0) {
			quantityOfProducts -= 1; //to avoid number with decimal point
		}
		BigDecimal priceByProductQuantity = new BigDecimal(quantityOfProducts * priceEveryTwoProducts);
		BigDecimal shippingPrice = new BigDecimal((distance/1000) * pricePerKm);
		return shippingPrice.add(priceByProductQuantity);
	}
	
	

}
