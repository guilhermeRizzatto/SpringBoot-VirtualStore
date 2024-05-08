package com.guilhermerizzatto.virtualstore.dtos.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.guilhermerizzatto.virtualstore.dtos.shoppingCart.ShoppingCartDTOforOrder;
import com.guilhermerizzatto.virtualstore.entities.Order;

public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private Long id;
    private BigDecimal total;
    private ZonedDateTime moment;

    private ShoppingCartDTOforOrder shoppingCart;

    public OrderDTO(Order obj){
        this.id = obj.getId();
        this.total = obj.getTotal();
        this.moment = obj.getMoment().atZone(ZoneId.of("America/Sao_Paulo"));
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

    public ZonedDateTime getMoment() {
        return moment;
    }

    public void setMoment(ZonedDateTime moment) {
        this.moment = moment;
    }

    public ShoppingCartDTOforOrder getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartDTOforOrder shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
