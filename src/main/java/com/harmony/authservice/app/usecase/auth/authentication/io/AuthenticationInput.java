package com.harmony.authservice.app.usecase.auth.authentication.io;

import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;

public class AuthenticationInput {
    private final Email email;
    private final Password rawPassword;

    public AuthenticationInput(Email email, Password rawPassword) {
        this.email = email;
        this.rawPassword = rawPassword;
    }

    public Email getEmail() {
        return email;
    }

    public Password getRawPassword() {
        return rawPassword;
    }
}
