package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Address;

import java.util.List;

public interface AddressDao {

    public Address findById(Long id);

    public Address insert(Address obj);

    public void update(Address obj);
    public void delete(Long id);

    public List<Address> findByCustomerId(Long customerId);
}
