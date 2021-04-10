package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;


public interface CredentialGateway {

    public Credential findById(Long id) throws Exception;

    public Credential findByEmail(String email) throws CredentialNotFoundException;

    public Credential save(Credential credential);

    public void deleteById(Long id);
}
