package com.harmony.authservice.infraestructure.passwordrecovery.createtoken.controller.io;

public class CreateTokenRequest {

    private String email;

    public CreateTokenRequest() {
    }

    public CreateTokenRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
