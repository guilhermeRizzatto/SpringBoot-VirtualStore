package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Order;

import java.util.List;

public interface OrderDao {

    public Order findByCustomerID(Long id);
    public Order insert(Order obj);
    public void update(Order order);
    public void delete(Long id);
}
