package com.harmony.authservice.app.usecase.passwordrecovery.createtoken.io;

import com.harmony.authservice.domain.credential.model.Email;

public class CreateTokenInput {

    private final Email email;

    public CreateTokenInput(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
