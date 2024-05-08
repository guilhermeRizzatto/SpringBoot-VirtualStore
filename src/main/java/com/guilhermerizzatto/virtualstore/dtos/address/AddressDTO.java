package com.guilhermerizzatto.virtualstore.dtos.address;

import com.guilhermerizzatto.virtualstore.dtos.customer.CustomerDTOforAddress;
import com.guilhermerizzatto.virtualstore.entities.Address;

import java.io.Serializable;

public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String street;
    private String district;
    private String city;
    private String state;

    private CustomerDTOforAddress customer;

    public AddressDTO(Address obj) {
        this.id = obj.getId();
        this.street = obj.getStreet();
        this.district = obj.getDistrict();
        this.city = obj.getCity();
        this.state = obj.getState();
        this.customer = new CustomerDTOforAddress(obj.getCustomer());
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

    public CustomerDTOforAddress getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTOforAddress customer) {
        this.customer = customer;
    }
}
