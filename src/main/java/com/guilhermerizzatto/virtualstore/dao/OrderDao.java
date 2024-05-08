package com.guilhermerizzatto.virtualstore.dao;

import java.math.BigDecimal;

import com.guilhermerizzatto.virtualstore.entities.Order;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

public interface OrderDao {

    public Order findByCustomerID(Long id);
    public Order insert(Order obj);
    public void delete(Long id);
    public BigDecimal getTotalPrice(ShoppingCart obj);
}
