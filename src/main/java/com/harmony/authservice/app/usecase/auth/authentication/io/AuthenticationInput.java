package com.harmony.authservice.app.usecase.auth.authentication.io;

import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.RawPassword;

public class AuthenticationInput {
    private final Email email;
    private final RawPassword rawPassword;

    public AuthenticationInput(Email email, RawPassword rawPassword) {
        this.email = email;
        this.rawPassword = rawPassword;
    }

    public Email getEmail() {
        return email;
    }

    public RawPassword getRawPassword() {
        return rawPassword;
    }
}
