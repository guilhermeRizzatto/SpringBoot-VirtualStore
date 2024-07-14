package com.guilhermerizzatto.virtualstore.controllers;


import com.guilhermerizzatto.virtualstore.dtos.customer.CustomerDTOResponse;
import com.guilhermerizzatto.virtualstore.dtos.register.RegisterCustomerRequest;
import com.guilhermerizzatto.virtualstore.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermerizzatto.virtualstore.dao.implementation.CustomerDaoImpl;
import com.guilhermerizzatto.virtualstore.dtos.login.LoginCustomerRequest;
import com.guilhermerizzatto.virtualstore.dtos.login.LoginResponse;
import com.guilhermerizzatto.virtualstore.entities.Customer;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {


	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private TokenService tokenService;

	private CustomerDaoImpl customerImpl = new CustomerDaoImpl();

	@PostMapping("/login/customer")
	public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginCustomerRequest loginRequest) {

		UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(usernamePassword);

		String token = tokenService.generateTokenCustomer((Customer) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponse(token, tokenService.expiresIn()));

	}

	@PostMapping("/register/customer")
	public ResponseEntity<CustomerDTOResponse> registerCustomer(@RequestBody RegisterCustomerRequest registerRequest) {

		if(customerImpl.findByUsername(registerRequest.getUsername()) != null) return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
		Customer obj = new Customer(
				registerRequest.getUsername(),
				registerRequest.getName(),
				registerRequest.getEmail(),
				registerRequest.getCpf(),
				registerRequest.getPhone(),
				encryptedPassword
		);

		Customer customer = customerImpl.insert(obj);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDTOResponse(customer));


	}
}
