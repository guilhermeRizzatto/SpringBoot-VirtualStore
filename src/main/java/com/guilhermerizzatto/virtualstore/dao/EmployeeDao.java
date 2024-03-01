package com.guilhermerizzatto.virtualstore.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guilhermerizzatto.virtualstore.entities.Employee;

@Repository
public interface EmployeeDao {
	
	public Employee findById(Long id);
	public List<Employee> findAll();
	public Employee insert(Employee obj);
	public void update(Employee employee);
	public void detele(Long id);
	
}
