package com.harmony.authservice.infraestructure.credential.gateway.db;

import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import com.harmony.authservice.domain.credential.model.*;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.infraestructure.credential.gateway.db.repository.entity.CredentialEntity;
import com.harmony.authservice.infraestructure.credential.gateway.db.repository.CredentialRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


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

        return new Credential(
                new CredentialId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                Role.valueOf(entity.getRole())
        );
    }

    @Override
    public Credential findByEmail(Email email) {
        CredentialEntity entity = credentialRepository
                .findByEmail(email.get())
                .orElseThrow(EntityNotFoundException::new);

        return new Credential(
                new CredentialId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                Role.valueOf(entity.getRole())
        );
    }

    @Override
    public Credential create(Credential credential) {
        CredentialEntity entity = credentialRepository.save(new CredentialEntity(
                credential.getEmail().get(),
                credential.getPassword().get(),
                credential.getRole().name()
        ));

        return new Credential(
                new CredentialId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                Role.valueOf(entity.getRole())
        );
    }

    @Override
    public void deleteById(CredentialId id) {
        credentialRepository.deleteById(id.get());
    }
}
