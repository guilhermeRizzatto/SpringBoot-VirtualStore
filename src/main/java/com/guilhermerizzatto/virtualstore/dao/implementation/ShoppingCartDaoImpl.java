package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ShoppingCartDao;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private Connection conn;

	public ShoppingCartDaoImpl() {
		conn = DBconnection.getConnection();
	}

	@Override //temporary query, in the future will be removed the WHERE clause because the fkey will be unique 
	public ShoppingCart findByCustomerId(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ShoppingCart cart = new ShoppingCart();
		
		try {
			st = conn.prepareStatement("""
					SELECT address.id AS address_id, address.street, address.district, address.city, address.state
					,customer.id AS customer_id, customer."name", customer.email, customer.cpf, customer.phone
					,product.id AS product_id, product."name", product.description, product.price, product.imageurl
					,productitem.quantity, productitem.price
					,shoppingcart.id AS shoppingcart_id, shoppingcart.shippingprice 
					FROM address
					INNER JOIN shoppingcart ON shoppingcart.address_id = address.id
					INNER JOIN customer ON customer.id = shoppingcart.customer_id 
					INNER JOIN productitem ON productitem.shoppingcart_id  = shoppingcart.id
					INNER JOIN product ON product.id = productitem.product_id
					WHERE shoppingcart.id = ?;
					""");

			st.setLong(1, id);

			rs = st.executeQuery();

			Address address = new Address();
			Customer customer = new Customer();
			
			while (rs.next()) {
				
				address.setId(rs.getLong(1));
				address.setStreet(rs.getString(2));
				address.setDistrict(rs.getString(3));
				address.setCity(rs.getString(4));
				address.setState(rs.getString(5));
				
				
				customer.setId(rs.getLong(6));
				customer.setName(rs.getString(7));
				customer.setEmail(rs.getString(8));
				customer.setCpf(rs.getString(9));
				customer.setPhone(rs.getString(10));
				
				Product product = new Product();
				
				product.setId(rs.getLong(11));
				product.setName(rs.getString(12));
				product.setDescription(rs.getString(13));
				product.setPrice(rs.getBigDecimal(14));
				product.setImageURL(rs.getString(15));
				
				ProductItem item = new ProductItem();
				item.setQuantity(rs.getInt(16));
				item.setPrice(rs.getBigDecimal(17));
				
				cart.setId(rs.getLong(18));
				cart.setShippingPrice(rs.getBigDecimal(19));
				cart.setAddress(address);
				cart.setCustomer(customer);
				
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
	public BigDecimal showShippingPriceWithCep(Long id, String cep) {
		return ShoppingCart.getTheShippingPriceWithCEP(cep);
	}

	@Override
	public void updateAddressInShoppingCart(Long id, Address address) {
		ShoppingCart obj = new ShoppingCart();
		obj.setAddress(address);
		obj.shippingPriceCalculator();

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE shoppingcart SET shippingPrice = ?, address_id = ? WHERE id = ?");

			st.setBigDecimal(1, obj.getShippingPrice());
			st.setLong(2, address.getId());

			st.setLong(3,id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnection.closeStatement(st);
		}
	}

	@Override
	public void delete(Long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM shoppingcart WHERE id = ?");

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnection.closeStatement(st);
		}
	}

}
