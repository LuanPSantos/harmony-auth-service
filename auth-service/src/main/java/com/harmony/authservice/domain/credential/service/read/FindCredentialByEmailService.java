package com.harmony.authservice.domain.credential.service.read;

import com.harmony.authservice.domain.credential.gateway.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import org.springframework.stereotype.Service;

@Service
public class FindCredentialByEmailService {

    private final CredentialGateway credentialGateway;

    public FindCredentialByEmailService(CredentialGateway credentialGateway) {
        this.credentialGateway = credentialGateway;
    }

    public Credential findByEmail(String email) throws CredentialNotFoundException {
        return credentialGateway.findByEmail(email);
    }
}
