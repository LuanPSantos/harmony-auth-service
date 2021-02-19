package com.harmony.authservice.domain.credential.service;

import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.repository.CredentialRepository;
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
                .orElseThrow(CredentialNotFoundException::new);
    }

    public Credential findByEmail(String email) throws CredentialNotFoundException {
        return credentialRepository
                .findByEmail(new Email(email))
                .orElseThrow(CredentialNotFoundException::new);
    }

    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    public void deleteById(Long id) {
        credentialRepository.deleteById(id);
    }
}
