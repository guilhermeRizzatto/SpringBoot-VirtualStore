package com.guilhermerizzatto.virtualstore.utils;

import com.google.maps.errors.ApiException;
import com.guilhermerizzatto.virtualstore.APIs.GoogleDirectionsAPI;
import com.guilhermerizzatto.virtualstore.entities.Address;

import java.io.IOException;

public class GetDistanceAPI {
    public static Long getDistanceWithAddress(Address address) {
        Long distance = Long.valueOf(0);

        try {

            distance = GoogleDirectionsAPI.getDistance(address);

        }catch (ApiException e) {
            System.out.println(e.getMessage());
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

       return distance;

    }

    public static Long getDistanceWithCEP(String cep) {
        Long distance = Long.valueOf(0);

        try {

            distance = GoogleDirectionsAPI.getDistanceWithCep(cep);

        }catch (ApiException e) {
            System.out.println(e.getMessage());
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return distance;

    }
}
