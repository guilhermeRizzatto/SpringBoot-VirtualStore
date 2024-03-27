package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ProductItemDao;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;

public class ProductItemDaoImpl implements ProductItemDao{
	
	private Connection conn;

    public ProductItemDaoImpl() {
        conn = DBconnection.getConnection();
    }

    @Override
    public List<ProductItem> findByShoppingCart(Long id) {
    	List<ProductItem> list = new ArrayList<ProductItem>();
    	PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT item.product_id,product.name,product.description,product.price AS unityPrice,product.imageurl,item.quantity,item.price AS totalPrice "
            		+ "FROM productitem item "
            		+ "INNER JOIN product product ON item.product_id = product.id "
            		+ "WHERE shoppingcart_id = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            while (rs.next()) {
            	Product product = new Product();
            	
            	product.setId(rs.getLong(1));
            	product.setName(rs.getString(2));
            	product.setDescription(rs.getString(3));
            	product.setPrice(rs.getBigDecimal(4));
            	product.setImageURL(rs.getString(5));
            	
            	ProductItem productItem = new ProductItem();
            	
            	productItem.setProduct(product);
            	productItem.setQuantity(rs.getInt(6));
            	productItem.setPrice(rs.getBigDecimal(7));

                list.add(productItem);
            }
            DBconnection.closeResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
        return list;
    }
    
	@Override
	public ProductItem insert(ProductItem obj) {
		ProductItem objToSave = new ProductItem(obj);
		PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO productitem (shoppingcart_id, product_id, quantity, price) VALUES (?,?,?,?)");

            st.setLong(1, objToSave.getShoppingCart().getId());
            st.setLong(2, objToSave.getProduct().getId());
            st.setInt(3, objToSave.getQuantity());
            st.setBigDecimal(4, objToSave.getPrice());

            st.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
		} finally {
            DBconnection.closeStatement(st);
        }
		return objToSave;
	}

	@Override
	public void update(ProductItem productItem) {
		ProductItem objToSave = new ProductItem(productItem);
		 PreparedStatement st = null;
	        try {
	            st = conn.prepareStatement("UPDATE productItem SET quantity = ?, price = ? WHERE product_id = ? AND shoppingcart_id = ?");

	            st.setInt(1, objToSave.getQuantity());
	            st.setBigDecimal(2, objToSave.getPrice());
	            
	            st.setLong(3,objToSave.getProduct().getId());
	            st.setLong(4,objToSave.getShoppingCart().getId());

	            st.executeUpdate();

	        } catch (SQLException e) {
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
