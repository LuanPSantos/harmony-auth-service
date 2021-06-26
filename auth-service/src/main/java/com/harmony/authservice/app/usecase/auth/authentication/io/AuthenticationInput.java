package com.harmony.authservice.app.usecase.auth.authentication.io;

import com.harmony.authservice.domain.credential.model.Email;

public class AuthenticationInput {
    private final Email email;
    private final String rawPassword;

    public AuthenticationInput(Email email, String password) {
        this.email = email;
        this.rawPassword = password;
    }

    public Email getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
