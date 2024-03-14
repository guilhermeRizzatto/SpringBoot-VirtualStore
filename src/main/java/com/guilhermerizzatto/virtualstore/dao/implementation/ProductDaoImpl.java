package com.guilhermerizzatto.virtualstore.dao.implementation;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ProductDao;
import com.guilhermerizzatto.virtualstore.entities.Employee;
import com.guilhermerizzatto.virtualstore.entities.Product;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private Connection conn;

    public ProductDaoImpl() {
        conn = DBconnection.getConnection();
    }

    @Override
    public Product findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM product WHERE id = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Product obj = new Product();
                obj.setId(rs.getLong(1));
                obj.setName(rs.getString(2));
                obj.setDescription(rs.getString(3));
                obj.setPrice(rs.getBigDecimal(4));
                obj.setImageURL(rs.getString(5));

                return obj;
            }
            DBconnection.closeResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product insert(Product obj) {
        Product objToSave = new Product(obj);
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO product (name, description, price, imageurl) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, objToSave.getName());
            st.setString(2, objToSave.getDescription());
            st.setBigDecimal(3, objToSave.getPrice());
            st.setString(4, objToSave.getImageURL());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    objToSave.setId(id);
                }
                DBconnection.closeResultSet(rs);
                return objToSave;
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
    public void update(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
