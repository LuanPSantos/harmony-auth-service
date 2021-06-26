package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.CredentialId;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;

public interface CredentialQueryGateway {
    Credential findById(CredentialId id) throws CredentialNotFoundException;

    Credential findByEmail(Email email);
}
