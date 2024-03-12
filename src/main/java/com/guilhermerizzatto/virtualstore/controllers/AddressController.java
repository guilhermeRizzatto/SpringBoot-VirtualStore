package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.dao.implementation.AddressDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.address.AddressDTO;
import com.guilhermerizzatto.virtualstore.entities.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
public class AddressController {


    AddressDaoImpl jdbcImpl = new AddressDaoImpl();

    @GetMapping(value = "/findByID/{id}")
    public ResponseEntity<AddressDTO> findByID(@PathVariable Long id)  {
        AddressDTO result = new AddressDTO(jdbcImpl.findById(id));
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> post(@RequestBody Address obj)  {
        AddressDTO result = new AddressDTO(jdbcImpl.insert(obj));
        if(result == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
