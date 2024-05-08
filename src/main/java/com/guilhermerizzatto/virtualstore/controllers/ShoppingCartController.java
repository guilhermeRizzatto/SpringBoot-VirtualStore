package com.guilhermerizzatto.virtualstore.controllers;

import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guilhermerizzatto.virtualstore.dao.implementation.ShoppingCartDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.shoppingCart.ShoppingCartDTO;
import com.guilhermerizzatto.virtualstore.entities.ShoppingCart;

import java.text.DecimalFormat;

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
	
	@GetMapping(value = "/getShippingPriceWithCep/shoppingCartId/{shoppingCartId}/cep/{cep}")
    public ResponseEntity<String> getShippingPriceWithCep(@PathVariable Long shoppingCartId,@PathVariable String cep){
		DecimalFormat df = new DecimalFormat("#.00");
        return ResponseEntity.status(HttpStatus.OK).body(df.format(shoppingCartImpl.showShippingPriceWithCep(shoppingCartId, cep)));
    }

	@PutMapping(value = "/put/shoppingCartId/{shoppingCartId}")
	public ResponseEntity<String> updateAddressInShoppingCart(@PathVariable Long shoppingCartId,@RequestBody Address address){
		shoppingCartImpl.updateAddressInShoppingCart(shoppingCartId, address);
		return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		shoppingCartImpl.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
	}



}
