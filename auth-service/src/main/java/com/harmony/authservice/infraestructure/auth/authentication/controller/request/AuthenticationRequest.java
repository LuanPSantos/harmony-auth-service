package com.harmony.authservice.infraestructure.auth.authentication.controller.request;

public class AuthenticationRequest {

    private String email;
    private String rawPassword;

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
