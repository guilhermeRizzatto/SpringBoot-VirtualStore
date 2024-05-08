package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.dao.implementation.CustomerDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.customer.CustomerDTO;
import com.guilhermerizzatto.virtualstore.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    CustomerDaoImpl customerImpl = new CustomerDaoImpl();

    @GetMapping(value = "/findByID/{id}")
    public ResponseEntity<CustomerDTO> findByID(@PathVariable Long id)  {
        CustomerDTO result = new CustomerDTO(customerImpl.findById(id));
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Customer>> findAll()  {
        List<Customer> list= customerImpl.findAll();
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @PostMapping
    public ResponseEntity<Customer> post(@RequestBody Customer obj)  {
        Customer result = customerImpl.insert(obj);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(value = "/put")
    public ResponseEntity<String> update(@RequestBody Customer obj){
        customerImpl.update(obj);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        customerImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
}
