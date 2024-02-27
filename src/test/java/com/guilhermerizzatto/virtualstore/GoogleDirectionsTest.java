package com.guilhermerizzatto.virtualstore;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.entities.Address;

class GoogleDirectionsTest {

	@Test //check the distance returned by GoogleDistanceAPI is correctly
	void checkTheDistanceOfGoogleDistanceApiReturns() throws ApiException, InterruptedException, IOException {
		
		Address customerAddress = new Address(); //Random address, only for the test
		customerAddress.setId(Long.valueOf(1));
		customerAddress.setStreet("Rua Margaret Ober, 34");
		customerAddress.setDistrict("Jardim Bonifacio");
		customerAddress.setCity("São Paulo");
		customerAddress.setCity("SP");
		
		//Always check the distance in google maps to be sure
		Assertions.assertEquals(Long.valueOf(99018), GoogleDirectionsAPI.getDistance(customerAddress)); //The number returned can be changed depending on situations like transit and others
		
	}

}
