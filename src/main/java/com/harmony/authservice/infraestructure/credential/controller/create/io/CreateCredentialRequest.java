package com.harmony.authservice.infraestructure.credential.controller.create.io;

import com.harmony.authservice.domain.credential.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateCredentialRequest {

    @NotBlank(message = "O campo 'email' não pode ser nulo ou vazio")
    private String email;
    @NotBlank(message = "O campo 'password' não pode ser nulo ou vazio")
    private String rawPassword;
    @NotNull(message = "O campo 'role' não pode ser nulo")
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public Role getRole() {
        if (role == null) {
            return Role.USER;
        }

        return role;
    }
}
