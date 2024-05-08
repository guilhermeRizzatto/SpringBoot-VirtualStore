package com.guilhermerizzatto.virtualstore.controllers;

import java.util.List;

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

import com.guilhermerizzatto.virtualstore.dao.implementation.ProductItemDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.productItem.ProductItemDTO;
import com.guilhermerizzatto.virtualstore.entities.ProductItem;

@RestController
@RequestMapping(value = "/productItem")
public class ProductItemController {
	
	ProductItemDaoImpl productItemImpl = new ProductItemDaoImpl();
	
	@PostMapping
	public ResponseEntity<ProductItem> post(@RequestBody ProductItem obj)  {
		ProductItem result = productItemImpl.insert(obj);
		if(result == null) {		
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping(value = "/findByShoppingCart/{id}")
    public ResponseEntity<List<ProductItemDTO>> findAll(@PathVariable Long id)  {
        List<ProductItemDTO> list= ProductItemDTO.createListDTO(productItemImpl.findByShoppingCart(id));
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
	
	@PutMapping(value = "/put")
    public ResponseEntity<String> update(@RequestBody ProductItem obj){
		productItemImpl.update(obj);
        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
    }
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> delete(@RequestBody ProductItem obj){
		productItemImpl.delete(obj);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
	}

}
