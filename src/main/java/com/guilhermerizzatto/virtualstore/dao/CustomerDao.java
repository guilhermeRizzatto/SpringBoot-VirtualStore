package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer findById(Long id);
    public List<Customer> findAll();
    public Customer insert(Customer obj);
    public void update(Customer customer);
    public void delete(Long id);


}


