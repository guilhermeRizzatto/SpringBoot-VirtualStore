package com.guilhermerizzatto.virtualstore.dtos.productItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;

public class ProductItemDTOforShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private Integer quantity;
	private BigDecimal totalPrice;
	
	public ProductItemDTOforShoppingCart(ProductItem obj) {
		super();
		this.product = obj.getProduct();
		this.quantity = obj.getQuantity();
		this.totalPrice = obj.getPrice();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ProductItemDTOforShoppingCart [product=" + product + ", quantity=" + quantity + ", price=" + totalPrice
				+ "]";
	}
	
	public static List<ProductItemDTOforShoppingCart> createListDTO (List<ProductItem> list){
        List<ProductItemDTOforShoppingCart> listDTO = new ArrayList<>();
        for(ProductItem x : list){
        	ProductItemDTOforShoppingCart obj = new ProductItemDTOforShoppingCart(x);
            listDTO.add(obj);
        }
        return listDTO;
    }
	
	
	
	

}
