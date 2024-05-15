package com.guilhermerizzatto.virtualstore.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guilhermerizzatto.virtualstore.dao.implementation.EmployeeDaoImpl;
import com.guilhermerizzatto.virtualstore.entities.Employee;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	EmployeeDaoImpl employeeImpl = new EmployeeDaoImpl();

	
	@GetMapping(value = "/findByID/ID={id}")
	public ResponseEntity<Employee> findByID(@PathVariable Long id)  {
		Employee result = employeeImpl.findById(id);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Employee>> findAll()  {
		List<Employee> list= employeeImpl.findAll();
		if(list.isEmpty()) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@PostMapping(value = "/post")
	public ResponseEntity<Employee> post(@RequestBody Employee obj)  {
		Employee result = employeeImpl.insert(obj);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PutMapping(value = "/put")
	public ResponseEntity<String> update(@RequestBody Employee obj){
		employeeImpl.update(obj);
		return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
	}

	@DeleteMapping(value = "/delete/ID={id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		employeeImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
	}

	
	
}
