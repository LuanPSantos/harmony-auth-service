package com.harmony.authservice.domain.credential.service.delete;

import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialService {

    private final CredentialService credentialService;

    public DeleteCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public void deleteById(Long id) {
        credentialService.deleteById(id);
    }
}
