package com.guilhermerizzatto.virtualstore.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.EmployeeDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Employee;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	EmployeeDaoImpl jdbcImpl = new EmployeeDaoImpl();
	
	
	@GetMapping(value = "/findByID/{id}")
	public ResponseEntity<Employee> findByID(@PathVariable Long id)  {
		Employee result = jdbcImpl.findById(id);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
	}
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Employee>> findAll()  {
		List<Employee> list= jdbcImpl.findAll();
		if(list.isEmpty()) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
	}
	
	
	@PostMapping
	public ResponseEntity<Employee> post(@RequestBody Employee obj)  {
		Employee result = jdbcImpl.insert(obj);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	
}
