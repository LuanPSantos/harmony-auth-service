package com.harmony.authservice.infraestructure.credential.gateway.db;

import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import com.harmony.authservice.domain.credential.model.*;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.infraestructure.credential.gateway.db.repository.entity.CredentialEntity;
import com.harmony.authservice.infraestructure.credential.gateway.db.repository.CredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialDatabaseGateway implements CredentialGateway {

    private final CredentialRepository credentialRepository;

    public CredentialDatabaseGateway(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential findById(CredentialId id) throws CredentialNotFoundException {
        CredentialEntity entity = credentialRepository
                .findById(id.get())
                .orElseThrow(CredentialNotFoundException::new);

        return new Credential.Builder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withEncodedPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    @Override
    public Credential findByEmail(Email email) throws CredentialNotFoundException {
        CredentialEntity entity = credentialRepository
                .findByEmail(email.get())
                .orElseThrow(CredentialNotFoundException::new);

        return new Credential.Builder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withEncodedPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    @Override
    public Credential create(Credential credential) {
        CredentialEntity entity = credentialRepository.save(new CredentialEntity(
                credential.getEmail().get(),
                credential.getPassword().get(),
                credential.getRole().name()
        ));

        return new Credential.Builder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withEncodedPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    @Override
    public void deleteById(CredentialId id) {
        credentialRepository.deleteById(id.get());
    }

    @Override
    public Credential update(Credential credential) {
        CredentialEntity entity = credentialRepository.save(new CredentialEntity(
                credential.getId().get(),
                credential.getEmail().get(),
                credential.getPassword().get(),
                credential.getRole().name()
        ));

        return new Credential.Builder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withEncodedPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }
}
