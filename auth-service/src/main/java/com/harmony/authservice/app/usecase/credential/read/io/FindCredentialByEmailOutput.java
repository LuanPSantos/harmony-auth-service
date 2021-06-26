package com.harmony.authservice.app.usecase.credential.read.io;

import com.harmony.authservice.domain.credential.model.Credential;

public class FindCredentialByEmailOutput {
    private final Credential credential;

    public FindCredentialByEmailOutput(Credential credential) {
        this.credential = credential;
    }

    public Credential getCredential() {
        return credential;
    }
}
