package com.guilhermerizzatto.virtualstore.dao.implementation;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.OrderDao;
import com.guilhermerizzatto.virtualstore.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    private Connection conn;

    public OrderDaoImpl() {
        conn = DBconnection.getConnection();
    }

    @Override
    public Order findByCustomerID(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Order order = new Order();
        try {
            st = conn.prepareStatement("SELECT customer.id AS customer_id, customer.name,customer.email,customer.cpf,customer.phone,cOrder.id AS order_id " +
                    ",cOrder.total,cOrder.moment,cart.id AS cart_id,cart.shippingprice,address.id as address_id,address.street,address.district " +
                    ",address.city,address.state,product.id AS product_id,product.name,product.description,product.price,product.imageurl " +
                    ",item.quantity,item.price AS subtotal FROM customer customer " +
                    "INNER JOIN customerorder cOrder ON cOrder.id = customer.id " +
                    "INNER JOIN shoppingcart cart ON cart.id = cOrder.shoppingcart_id " +
                    "INNER JOIN address ON cart.address_id = address.id " +
                    "INNER JOIN productitem item ON item.shoppingcart_id = cart.id " +
                    "INNER JOIN product ON item.product_id = product.id WHERE customer.id = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            ShoppingCart cart = new ShoppingCart();
            Customer customer = new Customer();

            while (rs.next()) {
                customer.setId(rs.getLong(1));
                customer.setName(rs.getString(2));
                customer.setEmail(rs.getString(3));
                customer.setCpf(rs.getString(4));
                customer.setPhone(rs.getString(5));

                order.setId(rs.getLong(6));
                order.setTotal(rs.getBigDecimal(7));
                //order.setMoment(rs.getTimestamp(8).toInstant());

                cart.setId(rs.getLong(9));
                cart.setShippingPrice(rs.getBigDecimal(10));

                Address address = new Address();
                address.setId(rs.getLong(11));
                address.setStreet(rs.getString(12));
                address.setDistrict(rs.getString(13));
                address.setCity(rs.getString(14));
                address.setState(rs.getString(15));

                Product product = new Product();
                product.setId(rs.getLong(16));
                product.setName(rs.getString(17));
                product.setDescription(rs.getString(18));
                product.setPrice(rs.getBigDecimal(19));
                product.setImageURL(rs.getString(20));

                ProductItem item = new ProductItem();
                item.setQuantity(rs.getInt(21));
                item.setPrice(rs.getBigDecimal(22));

                cart.setCustomer(customer);
                cart.setAddress(address);

                order.setShoppingCart(cart);

                item.setProduct(product);


                cart.getProducts().add(item);

            }

            order.setTotal(order.totalPrice());

            DBconnection.closeResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
        return order;
    }

    @Override
    public Order insert(Order obj) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Long id) {

    }
}