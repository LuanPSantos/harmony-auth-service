package com.harmony.authservice.domain.credential.service.read;

import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.dataprovider.CredentialDataProvider;
import org.springframework.stereotype.Service;

@Service
public class FindCredentialByEmailService {

    private final CredentialDataProvider credentialDataProvider;

    public FindCredentialByEmailService(CredentialDataProvider credentialDataProvider) {
        this.credentialDataProvider = credentialDataProvider;
    }

    public Credential findByEmail(String email) throws CredentialNotFoundException {
        return credentialDataProvider.findByEmail(email);
    }
}
