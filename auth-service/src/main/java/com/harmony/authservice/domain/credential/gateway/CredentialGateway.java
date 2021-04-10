package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.gateway.exception.CredentialNotFoundException;


public interface CredentialGateway {

    Credential findById(Long id) throws CredentialNotFoundException;

    Credential findByEmail(String email) throws CredentialNotFoundException;

    Credential save(Credential credential);

    void deleteById(Long id);
}
