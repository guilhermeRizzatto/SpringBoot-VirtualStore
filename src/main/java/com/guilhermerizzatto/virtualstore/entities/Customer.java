package com.guilhermerizzatto.virtualstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilhermerizzatto.virtualstore.enums.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private String password;
	private Role role = Role.CUSTOMER;
	
	private List<Address> adresses = new ArrayList<>();
	private ShoppingCart shoppingCart;
	
	public Customer() {
	}

	public Customer(Long id, String name, String email, String cpf, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.phone = phone;
		this.password = password;
	}

	public Customer(Customer obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.phone = obj.getPhone();
		this.password = obj.getPassword();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getRoleString() {
		return role.getRole();
	}

	public Role getRole() {
		return role;
	}

	public List<Address> getAdresses() {
		return adresses;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
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
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", cpf='" + cpf + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}
