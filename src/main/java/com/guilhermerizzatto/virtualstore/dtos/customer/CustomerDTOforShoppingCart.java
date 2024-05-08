package com.guilhermerizzatto.virtualstore.dtos.customer;

import java.io.Serializable;

import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.enums.Role;

public class CustomerDTOforShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;

	private Role role;

	public CustomerDTOforShoppingCart(Customer obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.phone = obj.getPhone();
		this.role = obj.getRole();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "CustomerDTOforShoppingCart [id=" + id + ", name=" + name + ", email=" + email + ", cpf=" + cpf
				+ ", phone=" + phone + ", role=" + role + "]";
	}

}
