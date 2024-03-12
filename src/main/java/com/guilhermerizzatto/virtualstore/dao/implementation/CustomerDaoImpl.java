package com.guilhermerizzatto.virtualstore.dao.implementation;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.CustomerDao;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Customer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private Connection conn;

    public CustomerDaoImpl(){
        conn = DBconnection.getConnection();
    }


    @Override
    public Customer findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        AddressDaoImpl addressImpl = new AddressDaoImpl();
        try {
            st = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Customer obj = new Customer();
                obj.setId(rs.getLong(1));
                obj.setName(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setCpf(rs.getString(4));
                obj.setPhone(rs.getString(5));
                obj.setPassword(rs.getString(6));

                for(Address x : addressImpl.findByCustomerId(id)){
                    obj.getAdresses().add(x);
                }

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
    public List<Customer> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Customer> list = new ArrayList<Customer>();
        try {
            st = conn.prepareStatement("SELECT * FROM customer ORDER BY id ASC");

            rs = st.executeQuery();

            while (rs.next()) {
                Customer obj = new Customer();
                obj.setId(rs.getLong(1));
                obj.setName(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setCpf(rs.getString(4));
                obj.setPhone(rs.getString(5));
                obj.setPassword(rs.getString(6));

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
    public Customer insert(Customer obj) {
        Customer objToSave = new Customer(obj);
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO customer (name, email, cpf, phone, password,role) VALUES (?,?,?,?,?,?::role)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, objToSave.getName());
            st.setString(2, objToSave.getEmail());
            st.setString(3, objToSave.getCpf());
            st.setString(4, objToSave.getPhone());
            st.setString(5, objToSave.getPassword());
            st.setString(6, objToSave.getRoleString());

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
    public void update(Customer obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE customer SET name = ?, email = ?, cpf = ?, phone = ? WHERE id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getCpf());
            st.setString(4, obj.getPhone());
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
            st = conn.prepareStatement("DELETE FROM customer WHERE id = ?");

            st.setLong(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeStatement(st);
        }
    }
}
