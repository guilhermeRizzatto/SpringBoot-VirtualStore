package com.guilhermerizzatto.virtualstore.dtos.shoppingCart;

import com.guilhermerizzatto.virtualstore.dtos.address.AddressDTOforShoppingCart;
import com.guilhermerizzatto.virtualstore.dtos.customer.CustomerDTOforShoppingCart;
import com.guilhermerizzatto.virtualstore.dtos.productItem.ProductItemDTOforShoppingCart;
import com.guilhermerizzatto.virtualstore.entities.Order;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTOforOrder implements Serializable {

    private Long id;
    private BigDecimal shippingPrice;

    private AddressDTOforShoppingCart address;
    private CustomerDTOforShoppingCart customer;
    private List<ProductItemDTOforShoppingCart> products = new ArrayList<>();

    public ShoppingCartDTOforOrder(ShoppingCart obj) {
        super();
        this.id = obj.getId();
        this.shippingPrice = obj.getShippingPrice();
        this.address = new AddressDTOforShoppingCart(obj.getAddress());
        this.customer = new CustomerDTOforShoppingCart(obj.getCustomer());
        this.products = ProductItemDTOforShoppingCart.createListDTO(obj.getProducts());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingPrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(shippingPrice);
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public AddressDTOforShoppingCart getAddress() {
        return address;
    }

    public void setAddress(AddressDTOforShoppingCart address) {
        this.address = address;
    }

    public CustomerDTOforShoppingCart getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTOforShoppingCart customer) {
        this.customer = customer;
    }

    public List<ProductItemDTOforShoppingCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItemDTOforShoppingCart> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTOforOrder{" +
                "id=" + id +
                ", shippingPrice=" + shippingPrice +
                ", address=" + address +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
