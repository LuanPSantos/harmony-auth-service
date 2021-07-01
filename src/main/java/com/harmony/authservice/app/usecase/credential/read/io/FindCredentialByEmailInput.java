package com.harmony.authservice.app.usecase.credential.read.io;

import com.harmony.authservice.domain.credential.model.Email;

public class FindCredentialByEmailInput {

    private final Email email;

    public FindCredentialByEmailInput(Email email) {
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
