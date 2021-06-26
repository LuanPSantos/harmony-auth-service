package com.harmony.authservice.app.usecase.credential.create.io;

import com.harmony.authservice.domain.credential.model.Credential;

public class CreateCredentialInput {
    private final Credential credential;

    public CreateCredentialInput(Credential credential) {
        this.credential = credential;
    }

    public Credential getCredential() {
        return credential;
    }
}
