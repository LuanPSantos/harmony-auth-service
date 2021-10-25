package com.harmony.authservice.app.usecase.credential.update.io;

import com.harmony.authservice.domain.credential.model.Credential;

public class UpdateCredentialOutput {
    private final Credential credential;

    public UpdateCredentialOutput(Credential credential) {
        this.credential = credential;
    }

    public Credential getCredential() {
        return credential;
    }
}
