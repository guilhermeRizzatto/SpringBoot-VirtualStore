package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ShoppingCartDao;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;
import com.guilhermerizzatto.virtualstore.utils.ShippingPriceCalculator;

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
			st = conn.prepareStatement(
					"SELECT Cart.id AS shoppingcart_id, Cart.shippingprice, Cart.customer_id, Address.id AS address_id, Address.street, Address.district, Address.city, "
					+ "Address.state,Customer.name,Customer.email,Customer.cpf,Customer.phone,Pitem.product_id, Pitem.quantity, Pitem.price AS totalprice, "
					+ "Product.name, Product.description, Product.price,Product.imageurl FROM shoppingcart Cart "
					+ "INNER JOIN address Address ON Address.id = Cart.address_id "
					+ "INNER JOIN customer Customer ON Customer.id = Cart.customer_id "
					+ "INNER JOIN productitem Pitem ON Pitem.shoppingcart_id = Cart.id "
					+ "INNER JOIN product Product ON Product.id = Pitem.product_id "
					+ "WHERE shoppingcart_id = ?");

			st.setLong(1, id);

			rs = st.executeQuery();

			Address address = new Address();
			Customer customer = new Customer();
			
			while (rs.next()) {
				cart.setId(rs.getLong(1));
				
				address.setId(rs.getLong(4));
				address.setStreet(rs.getString(5));
				address.setDistrict(rs.getString(6));
				address.setCity(rs.getString(7));
				address.setState(rs.getString(8));
				
				cart.setAddress(address);
							
				customer.setId(rs.getLong(3));
				customer.setName(rs.getString(9));
				customer.setEmail(rs.getString(10));
				customer.setCpf(rs.getString(11));
				customer.setPhone(rs.getString(12));
				
				cart.setCustomer(customer);
				
				Product product = new Product();
				
				product.setId(rs.getLong(13));
				product.setName(rs.getString(16));
				product.setDescription(rs.getString(17));
				product.setPrice(rs.getBigDecimal(18));
				product.setImageURL(rs.getString(19));
				
				ProductItem item = new ProductItem();
				item.setQuantity(rs.getInt(14));
				item.setPrice(rs.getBigDecimal(15));
				item.setProduct(product);
				item.setShoppingCart(cart);
				
				cart.getProducts().add(item);
			
			}
			
			cart.shippingPriceCalculator();
			
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
	public void updateShippingPriceWithCep(Long id,String cep) {
			
		PreparedStatement st = null;
	        try {
	            st = conn.prepareStatement("UPDATE shoppingcart SET shippingprice = ? WHERE id = ?");

	            st.setBigDecimal(1, ShippingPriceCalculator.calcForShoppingCart(GoogleDirectionsAPI.getDistanceWithCep(cep)));
	            st.setLong(2, id);	          
	            
	            st.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ApiException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
	            DBconnection.closeStatement(st);
	        }
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
