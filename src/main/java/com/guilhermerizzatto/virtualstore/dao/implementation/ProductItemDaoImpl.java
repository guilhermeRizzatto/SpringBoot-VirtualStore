package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ProductItemDao;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;

public class ProductItemDaoImpl implements ProductItemDao{
	
	private Connection conn;

    public ProductItemDaoImpl() {
        conn = DBconnection.getConnection();
    }

	@Override
	public ProductItem findByProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductItem> findAll() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
