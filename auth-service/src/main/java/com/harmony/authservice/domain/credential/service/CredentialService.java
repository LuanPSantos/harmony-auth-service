package com.harmony.userregistration.credential.service;

import com.harmony.userregistration.credential.model.Credential;
import com.harmony.userregistration.credential.model.Email;
import com.harmony.userregistration.credential.repository.CredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public Credential findById(Long id) throws Exception {
        return credentialRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Credenciais não encontradas."));
    }

    public Credential findByEmail(String email) throws Exception {
        return credentialRepository
                .findByEmail(new Email(email))
                .orElseThrow(() -> new Exception("Credentiais não encontradas por email"));
    }

    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }
}
