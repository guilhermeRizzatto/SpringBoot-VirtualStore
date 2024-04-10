package com.guilhermerizzatto.virtualstore.dtos.order;

import com.guilhermerizzatto.virtualstore.dtos.shoppingCart.ShoppingCartDTOforOrder;
import com.guilhermerizzatto.virtualstore.entities.Order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;

public class OrderDTO implements Serializable {

    private Long id;
    private BigDecimal total;
    private Instant moment;

    private ShoppingCartDTOforOrder shoppingCart;

    public OrderDTO(Order obj){
        this.id = obj.getId();
        this.total = obj.getTotal();
        this.moment = obj.getMoment();
        this.shoppingCart = new ShoppingCartDTOforOrder(obj.getShoppingCart());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotal() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(total);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public ShoppingCartDTOforOrder getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartDTOforOrder shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
