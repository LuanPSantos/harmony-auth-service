package com.harmony.authservice.app.usecase.credential.create.io;

import com.harmony.authservice.domain.credential.model.CredentialId;

public class CreateCredentialOutput {
    private final CredentialId credentialId;

    public CreateCredentialOutput(CredentialId credentialId) {
        this.credentialId = credentialId;
    }

    public CredentialId getCredentialId() {
        return credentialId;
    }
}
