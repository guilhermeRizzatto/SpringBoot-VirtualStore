package com.guilhermerizzatto.virtualstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.AddressDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.address.AddressDTO;
import com.guilhermerizzatto.virtualstore.entities.Address;

@RestController
@RequestMapping(value = "/address")
public class AddressController {


    AddressDaoImpl addressImpl = new AddressDaoImpl();

    @GetMapping(value = "/findByID/ID={id}")
    public ResponseEntity<AddressDTO> findByID(@PathVariable Long id)  {
        AddressDTO result = new AddressDTO(addressImpl.findById(id));
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<AddressDTO> post(@RequestBody Address obj)  {
        AddressDTO result = new AddressDTO(addressImpl.insert(obj));
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(value = "/put")
    public ResponseEntity<String> update(@RequestBody Address obj){
        addressImpl.update(obj);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }

    @DeleteMapping(value = "/delete/ID={id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        addressImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
}
