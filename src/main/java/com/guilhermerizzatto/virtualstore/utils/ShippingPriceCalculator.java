package com.guilhermerizzatto.virtualstore.utils;

import java.math.BigDecimal;

public class ShippingPriceCalculator {

	private static final Double pricePerKm = 0.09;
	private static final Double priceEveryTwoProducts = 0.39;

	public static BigDecimal calcForShoppingCart(Long distance) {
		return new BigDecimal((distance/1000) * pricePerKm);
	}
	
	public static BigDecimal calcForOrder(BigDecimal priceOfProducts, Integer quantityOfProducts) {
		
		BigDecimal priceForFreeShippingPrice = new BigDecimal(1999.99);//1299.00
		if(priceOfProducts.compareTo(priceForFreeShippingPrice) > 0) {
			return new BigDecimal(0);
		}
		//every 2 products in shoppingCart price increase 0.39
		if((quantityOfProducts % 2) != 0) {
			quantityOfProducts -= 1; //to avoid number with decimal point
		}
		
		return new BigDecimal((quantityOfProducts / 2) * priceEveryTwoProducts);
	}
	
	

}
