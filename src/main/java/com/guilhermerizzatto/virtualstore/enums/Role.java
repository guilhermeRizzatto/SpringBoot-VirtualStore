package com.guilhermerizzatto.virtualstore.enums;

public enum Role {

    EMPLOYEE("EMPLOYEE"),
    CUSTOMER("CUSTOMER");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
