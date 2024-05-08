package com.guilhermerizzatto.virtualstore.dao;

import com.guilhermerizzatto.virtualstore.entities.Employee;

import java.util.List;


public interface EmployeeDao {
	
	public Employee findById(Long id);
	public List<Employee> findAll();
	public Employee insert(Employee obj);
	public void update(Employee employee);
	public void delete(Long id);
	
}
