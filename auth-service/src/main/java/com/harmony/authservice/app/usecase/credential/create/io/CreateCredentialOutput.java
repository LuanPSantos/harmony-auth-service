package com.harmony.authservice.app.usecase.credential.create.io;

import com.harmony.authservice.domain.credential.model.Role;

public class CreateCredentialOutput {
    private final Long id;
    private final String email;
    private final String password;
    private final Role role;

    public CreateCredentialOutput(Long id, String email, String password, Role role) {
        this.id = id;
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

    public Long getId() {
        return id;
    }
}
