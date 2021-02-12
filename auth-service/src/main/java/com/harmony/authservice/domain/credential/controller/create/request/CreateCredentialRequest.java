package com.harmony.authservice.domain.credential.controller.create.request;

import javax.validation.constraints.NotBlank;

public class CreateCredentialRequest {

    @NotBlank(message = "O campo 'email' não pode ser nulo ou vazio")
    private String email;
    @NotBlank(message = "O campo 'password' não pode ser nulo ou vazio")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
