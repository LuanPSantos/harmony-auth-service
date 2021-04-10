package com.harmony.authservice.infraestructure.credential.gateway;

import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.infraestructure.credential.gateway.repository.CredentialRepository;
import org.springframework.stereotype.Service;


@Service
public class CredentialDatabaseGateway implements CredentialGateway {

    private final CredentialRepository credentialRepository;

    public CredentialDatabaseGateway(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential findById(Long id) throws Exception {
        return credentialRepository
                .findById(id)
                .orElseThrow(CredentialNotFoundException::new);
    }

    @Override
    public Credential findByEmail(String email) throws CredentialNotFoundException {
        return credentialRepository
                .findByEmail(new Email(email))
                .orElseThrow(CredentialNotFoundException::new);
    }

    @Override
    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public void deleteById(Long id) {
        credentialRepository.deleteById(id);
    }
}
