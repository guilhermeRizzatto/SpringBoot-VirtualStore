package com.guilhermerizzatto.virtualstore.dtos.customer;

import com.guilhermerizzatto.virtualstore.dtos.address.AddressDTOforCustomer;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.enums.Role;

import java.io.Serializable;
import java.util.List;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;

    private Role role;

    private List<AddressDTOforCustomer> addresses;

    public CustomerDTO(Customer obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.cpf = obj.getCpf();
        this.phone = obj.getPhone();
        this.role = obj.getRole();
        this.addresses = AddressDTOforCustomer.createListDTO(obj.getAdresses());
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

    public List<AddressDTOforCustomer> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTOforCustomer> addresses) {
        this.addresses = addresses;
    }
}
