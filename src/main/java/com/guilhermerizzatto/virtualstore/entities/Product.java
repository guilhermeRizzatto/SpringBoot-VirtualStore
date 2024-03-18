package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String imageURL;
	
	public Product() {
	}

	public Product(Long id, String name, String description, BigDecimal price, String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageURL = imageURL;
	}

	public Product(Product obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
		this.price = obj.getPrice();
		this.imageURL = obj.getImageURL();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imageURL=" + imageURL;
	}
	
	

}
