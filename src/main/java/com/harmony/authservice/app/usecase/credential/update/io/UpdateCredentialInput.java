package com.harmony.authservice.app.usecase.credential.update.io;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Password;

public class UpdateCredentialInput {
    private final Credential credential;
    private final Password oldRawPassword;

    public UpdateCredentialInput(Credential credential, Password oldPassword) {
        this.credential = credential;
        this.oldRawPassword = oldPassword;
    }

    public Password getOldRawPassword() {
        return oldRawPassword;
    }

    public Credential getCredential() {
        return credential;
    }
}
