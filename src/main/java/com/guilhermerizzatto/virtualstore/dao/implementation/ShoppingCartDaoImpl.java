package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ShoppingCartDao;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private Connection conn;

	public ShoppingCartDaoImpl() {
		conn = DBconnection.getConnection();
	}

	@Override //temporary query, in the future, the query will return the address to able to calculate the shippingPrice, and remove the WHERE clause because the fkey will be unique 
	public ShoppingCart findByCustomerId(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		ShoppingCart cart = new ShoppingCart();
		try {
			st = conn.prepareStatement(
					"SELECT Cart.id AS shoppingcart_id, Cart.shippingprice, Cart.customer_id, Customer.name, "
							+ "Pitem.product_id, Pitem.quantity, Pitem.price AS totalprice, Product.name, Product.description, Product.price, "
							+ "Product.imageurl FROM shoppingcart Cart "
							+ "INNER JOIN customer Customer ON Customer.id = Cart.customer_id "
							+ "INNER JOIN productitem Pitem ON Pitem.shoppingcart_id = Cart.id "
							+ "INNER JOIN product Product ON Product.id = Pitem.product_id "
							+ "WHERE shoppingcart_id = ?");

			st.setLong(1, id);

			rs = st.executeQuery();

			
			while (rs.next()) {
				cart.setId(rs.getLong(1));
							
				Customer customer = new Customer();
				customer.setId(rs.getLong(3));
				customer.setName(rs.getString(4));
				
				cart.setCustomer(customer);
				
				Product product = new Product();
				
				product.setId(rs.getLong(5));
				product.setName(rs.getString(8));
				product.setDescription(rs.getString(9));
				product.setPrice(rs.getBigDecimal(10));
				product.setImageURL(rs.getString(11));
				
				ProductItem item = new ProductItem();
				item.setQuantity(rs.getInt(6));
				item.setPrice(rs.getBigDecimal(7));
				item.setProduct(product);
				item.setShoppingCart(cart);
				
				cart.getProducts().add(item);
			
			}
			DBconnection.closeResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnection.closeStatement(st);
		}
		return cart;
	}

	@Override
	public ShoppingCart insert(ShoppingCart obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO shoppingcart (shippingPrice, customer_id, address_id) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setBigDecimal(1, obj.getShippingPrice());
			st.setLong(2, obj.getCustomer().getId());
			st.setLong(3, obj.getAddress().getId());

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
	public void update(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
