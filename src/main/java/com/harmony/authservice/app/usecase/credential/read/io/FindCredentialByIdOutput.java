package com.harmony.authservice.app.usecase.credential.read.io;

import com.harmony.authservice.domain.credential.model.Credential;

public class FindCredentialByIdOutput {
    private final Credential credential;

    public FindCredentialByIdOutput(Credential credential) {
        this.credential = credential;
    }

    public Credential getCredential() {
        return credential;
    }
}
