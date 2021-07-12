package com.harmony.authservice.app.usecase.credential.read.io;

import com.harmony.authservice.domain.credential.model.CredentialId;

public class FindCredentialByIdInput {

    private final CredentialId credentialId;

    public FindCredentialByIdInput(CredentialId credentialId) {
        this.credentialId = credentialId;
    }

    public CredentialId getCredentialId() {
        return credentialId;
    }
}
