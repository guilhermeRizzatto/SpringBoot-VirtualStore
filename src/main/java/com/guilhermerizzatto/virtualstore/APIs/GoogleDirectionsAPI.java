package com.guilhermerizzatto.virtualstore.APIs;

import java.io.IOException;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Distance;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.guilhermerizzatto.virtualstore.entities.Address;
import com.guilhermerizzatto.virtualstore.entities.Stock;

public class GoogleDirectionsAPI {
	
	private static GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyDR3niIrCbYpug3rMfz9FAX6JdB6nSNcbQ")
		    .build();
	
	 public static Long getDistance(Address address) throws ApiException, InterruptedException, IOException {
		 Distance distance = null;
		 DirectionsResult result = DirectionsApi.newRequest(context)
				 .mode(TravelMode.DRIVING)
				 .origin(Stock.local)
				 .destination(address.getStreet() + "," + address.getState())
				 .units(Unit.METRIC)
				 .await();
		 DirectionsRoute[] matrixRoute = result.routes;
		 for (DirectionsRoute directionsRoute : matrixRoute) {
			 DirectionsLeg[] matrixLeg = directionsRoute.legs;
			 for (DirectionsLeg legs : matrixLeg) {
				 distance = legs.distance;
			}
		}
		return distance.inMeters;
	 }

}
