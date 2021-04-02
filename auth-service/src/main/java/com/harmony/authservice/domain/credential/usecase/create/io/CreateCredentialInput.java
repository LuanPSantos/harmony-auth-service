package com.harmony.authservice.domain.credential.usecase.create.io;

import com.harmony.authservice.domain.credential.model.Role;

public class CreateCredentialInput {
    private String email;
    private String password;
    private Role role;

    public CreateCredentialInput(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return role;
    }
}
