package com.harmony.authservice.app.usecase.credential.create.io;

import com.harmony.authservice.domain.credential.model.Role;

public class CreateCredentialInput {
    private final String email;
    private final String password;
    private final Role role;

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
