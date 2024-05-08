package com.guilhermerizzatto.virtualstore.dao.implementation;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.StockDao;
import com.guilhermerizzatto.virtualstore.entities.Product;
import com.guilhermerizzatto.virtualstore.entities.Stock;

import java.sql.*;
import java.util.Map;

public class StockDaoImpl implements StockDao {

    private Connection conn;

    public StockDaoImpl() {
        conn = DBconnection.getConnection();
    }


    @Override
    public Map<Product, Integer> showProductsAndQuantity() {
        Stock stock = new Stock();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT STOCK.product_id,STOCK.quantity, PRODUCT.name,PRODUCT.description,PRODUCT.price,PRODUCT.imageurl FROM stock STOCK INNER JOIN product PRODUCT ON STOCK.product_id = PRODUCT.id");

            rs = st.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                Integer quantity = rs.getInt(2);

                product.setId(rs.getLong(1));
                product.setName(rs.getString(3));
                product.setDescription(rs.getString(4));
                product.setPrice(rs.getBigDecimal(5));
                product.setImageURL(rs.getString(6));

                stock.addProductAndQuantity(product,quantity);
            }
            DBconnection.closeResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
        return stock.get();
    }

    @Override
    public void insertProductAndQuantity(Product obj, Integer quantity) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO stock (product_id, quantity) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setLong(1, obj.getId());
            st.setInt(2, quantity);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
    }

    @Override
    public void deleteProductAndQuantity(Long id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM stock WHERE product_id = ?");

            st.setLong(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
    }


	@Override
	public void updateQuantity(Long id, Integer quantity) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE stock SET quantity = ? WHERE product_id = ?");

			st.setInt(1, quantity);
			st.setLong(2, id);

			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnection.closeStatement(st);
		}
	}
}
