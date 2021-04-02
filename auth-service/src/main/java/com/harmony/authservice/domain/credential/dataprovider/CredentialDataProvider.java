package com.harmony.authservice.domain.credential.dataprovider;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;


public interface CredentialDataProvider {

    public Credential findById(Long id) throws Exception;

    public Credential findByEmail(String email) throws CredentialNotFoundException;

    public Credential save(Credential credential);

    public void deleteById(Long id);
}
