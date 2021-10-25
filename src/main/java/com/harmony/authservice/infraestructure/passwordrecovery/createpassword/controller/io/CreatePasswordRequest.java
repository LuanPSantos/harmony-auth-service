package com.harmony.authservice.infraestructure.passwordrecovery.createpassword.controller.io;

public class CreatePasswordRequest {

    private String password;

    public CreatePasswordRequest() {
    }

    public CreatePasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
