package com.harmony.authservice.app.usecase.credential.update.io;

import com.harmony.authservice.domain.credential.model.CredentialId;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.RawPassword;

public class UpdateCredentialInput {
    private final CredentialId id;
    private final Email email;
    private final RawPassword newRawPassword;
    private final RawPassword oldRawPassword;

    public UpdateCredentialInput(RawPassword oldRawPassword, CredentialId id, Email email, RawPassword newRawPassword) {
        this.id = id;
        this.email = email;
        this.newRawPassword = newRawPassword;
        this.oldRawPassword = oldRawPassword;
    }

    public CredentialId getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public RawPassword getNewRawPassword() {
        return newRawPassword;
    }

    public RawPassword getOldRawPassword() {
        return oldRawPassword;
    }
}
