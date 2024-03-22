package com.guilhermerizzatto.virtualstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.ProductItemDaoImpl;
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

}
