package com.harmony.authservice.domain.credential.controller.create.request;

import com.harmony.authservice.domain.credential.model.Role;

import javax.validation.constraints.NotBlank;

public class CreateCredentialRequest {

    @NotBlank(message = "O campo 'email' não pode ser nulo ou vazio")
    private String email;
    @NotBlank(message = "O campo 'password' não pode ser nulo ou vazio")
    private String password;
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        if(role == null) {
            return Role.USER;
        }

        return role;
    }
}
