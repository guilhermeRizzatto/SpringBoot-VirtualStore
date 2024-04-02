package com.guilhermerizzatto.virtualstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.ShoppingCartDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.shoppingCart.ShoppingCartDTO;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

@RestController
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {
	
	ShoppingCartDaoImpl shoppingCartImpl = new ShoppingCartDaoImpl();
	
	@PostMapping
	public ResponseEntity<ShoppingCart> post(@RequestBody ShoppingCart obj)  {
		ShoppingCart result = shoppingCartImpl.insert(obj);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping(value = "/findByCustomerId/{id}")
    public ResponseEntity<ShoppingCartDTO> findByCustomerId(@PathVariable Long id)  {
		ShoppingCartDTO result = new ShoppingCartDTO(shoppingCartImpl.findByCustomerId(id));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	
	@PutMapping(value = "/put/shoppingCartId/{shoppingCartId}/cep/{cep}")
    public ResponseEntity<String> updateShippingPriceWithCep(@PathVariable Long shoppingCartId,@PathVariable String cep){
		shoppingCartImpl.updateShippingPriceWithCep(shoppingCartId, cep);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }

}
