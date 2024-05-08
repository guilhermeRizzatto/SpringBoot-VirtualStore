package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.OrderDao;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.entities.Order;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

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
                    "INNER JOIN customerorder cOrder ON cOrder.customer_id = customer.id " +
                    "INNER JOIN shoppingcart cart ON cart.id = cOrder.shoppingcart_id " +
                    "INNER JOIN address ON cart.address_id = address.id " +
                    "INNER JOIN productitem item ON item.shoppingcart_id = cart.id " +
                    "INNER JOIN product ON item.product_id = product.id WHERE cOrder.customer_id = ?");

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
                order.setMoment(rs.getTimestamp(8).toInstant());

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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO customerorder (total, moment, shoppingcart_id, customer_id) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            st.setBigDecimal(1, obj.getTotal());
            st.setTimestamp(2, Timestamp.from(Instant.now()));
            st.setLong(3, obj.getShoppingCart().getId());
            st.setLong(4, obj.getShoppingCart().getCustomer().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    obj.setId(id);
                }
                DBconnection.closeResultSet(rs);
                return obj;
            } else {
                throw new IOException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
        return null;
    }

    @Override
    public void delete(Long id) {

    }

	@Override
	public BigDecimal getTotalPrice(ShoppingCart obj) {
		Order order = new Order();
		order.setShoppingCart(obj);
		
		return order.totalPrice();
	}
}
