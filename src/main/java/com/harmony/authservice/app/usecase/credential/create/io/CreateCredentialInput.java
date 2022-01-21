package com.harmony.authservice.app.usecase.credential.create.io;

import com.harmony.authservice.domain.credential.model.*;

public class CreateCredentialInput {

    private final Email email;
    private final RawPassword rawPassword;
    private final Role role;

    public CreateCredentialInput(Email email, RawPassword rawPassword, Role role) {
        this.email = email;
        this.rawPassword = rawPassword;
        this.role = role;
    }

    public Email getEmail() {
        return email;
    }

    public RawPassword getRawPassword() {
        return rawPassword;
    }

    public Role getRole() {
        return role;
    }
}
