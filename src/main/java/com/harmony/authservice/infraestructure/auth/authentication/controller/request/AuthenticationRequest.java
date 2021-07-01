package com.harmony.authservice.infraestructure.auth.authentication.controller.request;

import javax.validation.constraints.NotBlank;

public class AuthenticationRequest {

    @NotBlank(message = "O campo 'email' não pode ser nulo")
    private String email;
    @NotBlank(message = "O campo 'rawPassword' não pode ser nulo")
    private String rawPassword;

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
