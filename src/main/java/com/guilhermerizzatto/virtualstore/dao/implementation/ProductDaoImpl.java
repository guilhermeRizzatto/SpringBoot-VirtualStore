package com.guilhermerizzatto.virtualstore.dao.implementation;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.ProductDao;
import com.guilhermerizzatto.virtualstore.entities.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<Product>();
        try {
            st = conn.prepareStatement("SELECT * FROM product ORDER BY id ASC");

            rs = st.executeQuery();

            while (rs.next()) {
                Product obj = new Product();
                obj.setId(rs.getLong(1));
                obj.setName(rs.getString(2));
                obj.setDescription(rs.getString(3));
                obj.setPrice(rs.getBigDecimal(4));
                obj.setImageURL(rs.getString(5));

                list.add(obj);
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
    public Product insert(Product obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO product (name, description, price, imageurl) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            st.setBigDecimal(3, obj.getPrice());
            st.setString(4, obj.getImageURL());

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
    public void update(Product obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE product SET name = ?, description = ?, price = ?, imageurl = ? WHERE id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            st.setBigDecimal(3, obj.getPrice());
            st.setString(4, obj.getImageURL());
            st.setLong(5,obj.getId());

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
            st = conn.prepareStatement("DELETE FROM product WHERE id = ?");

            st.setLong(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
    }
}
