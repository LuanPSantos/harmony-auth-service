package com.harmony.authservice.infraestructure.credential.dataprovider;

import com.harmony.authservice.domain.credential.dataprovider.CredentialDataProvider;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.infraestructure.credential.dataprovider.repository.CredentialRepository;
import org.springframework.stereotype.Service;


@Service
public class CredentialDatabaseDataProvider implements CredentialDataProvider {

    private final CredentialRepository credentialRepository;

    public CredentialDatabaseDataProvider(CredentialRepository credentialRepository) {
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
