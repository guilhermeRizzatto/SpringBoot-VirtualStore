package com.guilhermerizzatto.virtualstore.entities;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String street;
	private String district;
	private String city;
	private String state;
	
	private Customer customer;

	public Address() {
	}


	public Address(Long id, String street, String district, String city, String state, Customer customer) {
		super();
		this.id = id;
		this.street = street;
		this.district = district;
		this.city = city;
		this.state = state;
		this.customer = customer;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", district=" + district + ", city=" + city + ", state="
				+ state + "]";
	}
	
	
	

}
