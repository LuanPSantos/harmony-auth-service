package com.harmony.authservice.app.usecase.credential.update.io;

import com.harmony.authservice.domain.credential.model.Credential;

public class UpdateCredentialInput {
    private final Credential credential;

    public UpdateCredentialInput(Credential credential) {
        this.credential = credential;
    }

    public Credential getCredential() {
        return credential;
    }
}
