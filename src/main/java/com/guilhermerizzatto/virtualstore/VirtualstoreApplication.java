package com.guilhermerizzatto.virtualstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class VirtualstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualstoreApplication.class, args);
	}

}
