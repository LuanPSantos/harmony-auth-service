package com.harmony.authservice.domain.credential.service.delete;

import com.harmony.authservice.domain.credential.dataprovider.CredentialDataProvider;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialService {

    private final CredentialDataProvider credentialDataProvider;

    public DeleteCredentialService(CredentialDataProvider credentialDataProvider) {
        this.credentialDataProvider = credentialDataProvider;
    }

    public void deleteById(Long id) {
        credentialDataProvider.deleteById(id);
    }
}
