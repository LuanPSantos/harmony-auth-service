package com.harmony.authservice.app.usecase.credential.delete.io;

import com.harmony.authservice.domain.credential.model.CredentialId;

public class DeleteCredentialInput {
    private final CredentialId credentialId;

    public DeleteCredentialInput(CredentialId credentialId) {
        this.credentialId = credentialId;
    }

    public CredentialId getCredentialId() {
        return credentialId;
    }
}
