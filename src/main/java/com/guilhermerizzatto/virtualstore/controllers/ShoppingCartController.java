package com.guilhermerizzatto.virtualstore.controllers;

import java.text.DecimalFormat;

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

import com.guilhermerizzatto.virtualstore.dao.implementation.ShoppingCartDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.shoppingCart.ShoppingCartDTO;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

@RestController
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {
	
	ShoppingCartDaoImpl shoppingCartImpl = new ShoppingCartDaoImpl();
	
	@PostMapping(value = "/post")
	public ResponseEntity<ShoppingCart> post(@RequestBody ShoppingCart obj)  {
		ShoppingCart result = shoppingCartImpl.insert(obj);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping(value = "/findByCustomerID/ID={id}")
    public ResponseEntity<ShoppingCartDTO> findByCustomerId(@PathVariable Long id)  {
		ShoppingCartDTO result = new ShoppingCartDTO(shoppingCartImpl.findByCustomerId(id));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
	
	@GetMapping(value = "/getShippingPriceWithCEP/shoppingCartID={shoppingCartID}/cep={cep}")
    public ResponseEntity<String> getShippingPriceWithCep(@PathVariable Long shoppingCartID,@PathVariable String cep){
		DecimalFormat df = new DecimalFormat("#.00");
        return ResponseEntity.status(HttpStatus.OK).body(df.format(shoppingCartImpl.showShippingPriceWithCep(shoppingCartID, cep)));
    }

	@PutMapping(value = "/put/shoppingCartID={shoppingCartID}")
	public ResponseEntity<String> updateAddressInShoppingCart(@PathVariable Long shoppingCartID,@RequestBody Address address){
		shoppingCartImpl.updateAddressInShoppingCart(shoppingCartID, address);
		return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
	}

	@DeleteMapping(value = "/delete/ID={id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		shoppingCartImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
	}



}
