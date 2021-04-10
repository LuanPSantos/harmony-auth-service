package com.harmony.authservice.domain.credential.service.delete;

import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialService {

    private final CredentialGateway credentialGateway;

    public DeleteCredentialService(CredentialGateway credentialGateway) {
        this.credentialGateway = credentialGateway;
    }

    public void deleteById(Long id) {
        credentialGateway.deleteById(id);
    }
}
