package com.guilhermerizzatto.virtualstore.dtos.login;

import java.io.Serializable;

public class LoginResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String jwtValues;
	private Long expiresIn;
	
	public LoginResponse(String jwtValues, Long expiresIn) {
		this.jwtValues = jwtValues;
		this.expiresIn = expiresIn;
	}

	public String getJwtValues() {
		return jwtValues;
	}

	public void setJwtValues(String jwtValues) {
		this.jwtValues = jwtValues;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return "LoginResponse [jwtValues=" + jwtValues + ", expiresIn=" + expiresIn + "]";
	}
	
	

}
