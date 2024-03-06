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
	
	EmployeeDaoImpl jdbcImpl = new EmployeeDaoImpl();
	
	
	@GetMapping(value = "/findByID/{id}")
	public ResponseEntity<Employee> findByID(@PathVariable Long id)  {
		Employee result = jdbcImpl.findById(id);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Employee>> findAll()  {
		List<Employee> list= jdbcImpl.findAll();
		if(list.isEmpty()) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@PostMapping
	public ResponseEntity<Employee> post(@RequestBody Employee obj)  {
		Employee result = jdbcImpl.insert(obj);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PutMapping(value = "/put")
	public ResponseEntity<String> update(@RequestBody Employee obj){
		jdbcImpl.update(obj);
		return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		jdbcImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
	}

	
	
}
