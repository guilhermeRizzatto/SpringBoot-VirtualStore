package com.guilhermerizzatto.virtualstore.dao.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.guilhermerizzatto.virtualstore.DB.DBconnection;
import com.guilhermerizzatto.virtualstore.dao.EmployeeDao;
import com.guilhermerizzatto.virtualstore.entities.Employee;


public class EmployeeDaoImpl implements EmployeeDao {

	private Connection conn;

	public EmployeeDaoImpl() {
		conn = DBconnection.getConnection();
	}

	@Override
	public Employee findById(Long id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM employee WHERE id = ?");

			st.setLong(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Employee obj = new Employee();
				obj.setId(rs.getLong(1));
				obj.setName(rs.getString(2));
				obj.setEmail(rs.getString(3));
				obj.setCpf(rs.getString(4));
				obj.setPhone(rs.getString(5));

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
	public List<Employee> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			st = conn.prepareStatement("SELECT * FROM employee ORDER BY id ASC");

			rs = st.executeQuery();

			while (rs.next()) {
				Employee obj = new Employee();
				obj.setId(rs.getLong(1));
				obj.setName(rs.getString(2));
				obj.setEmail(rs.getString(3));
				obj.setCpf(rs.getString(4));
				obj.setPhone(rs.getString(5));

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
	public Employee insert(Employee obj) {
		Employee objToSave = new Employee(obj);
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO employee (name, email, cpf, phone, role) VALUES (?,?,?,?,?::role)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, objToSave.getName());
			st.setString(2, objToSave.getEmail());
			st.setString(3, objToSave.getCpf());
			st.setString(4, objToSave.getPhone());
			st.setString(5, objToSave.getRole());

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
	public void update(Employee obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE employee SET name = ?, email = ?, cpf = ?, phone = ? WHERE id = ?");

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
			st = conn.prepareStatement("DELETE FROM employee WHERE id = ?");

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconnection.closeStatement(st);
		}
	}

}
