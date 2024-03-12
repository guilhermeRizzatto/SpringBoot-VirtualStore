package com.guilhermerizzatto.virtualstore.dao.implementation;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.AddressDao;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Customer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private Connection conn;

    public AddressDaoImpl(){
        conn = DBconnection.getConnection();
    }


    @Override
    public Address findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        CustomerDaoImpl customerImpl = new CustomerDaoImpl();
        try {
            st = conn.prepareStatement("SELECT * FROM address WHERE id = ?");

            st.setLong(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Address obj = new Address();
                obj.setId(rs.getLong(1));
                obj.setStreet(rs.getString(2));
                obj.setDistrict(rs.getString(3));
                obj.setCity(rs.getString(4));
                obj.setState(rs.getString(5));

                Long customerID = rs.getLong(6);


                Customer customer = customerImpl.findById(customerID);

                obj.setCustomer(customer);

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
    public Address insert(Address obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO address (street, district, city, state, customer_id) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getStreet());
            st.setString(2, obj.getDistrict());
            st.setString(3, obj.getCity());
            st.setString(4, obj.getState());
            st.setLong(5, obj.getCustomer().getId());

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

    @Override //to use in Customer only
    public List<Address> findByCustomerId(Long customerId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Address> list = new ArrayList<Address>();
        try {
            st = conn.prepareStatement("SELECT * FROM address WHERE customer_id = ? ORDER BY customer_id ASC");

            st.setLong(1, customerId);

            rs = st.executeQuery();

            while (rs.next()) {
                Address obj = new Address();
                obj.setId(rs.getLong(1));
                obj.setStreet(rs.getString(2));
                obj.setDistrict(rs.getString(3));
                obj.setCity(rs.getString(4));
                obj.setState(rs.getString(5));

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
}
