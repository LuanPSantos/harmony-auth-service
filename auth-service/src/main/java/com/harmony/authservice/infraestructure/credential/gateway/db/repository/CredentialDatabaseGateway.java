package com.harmony.authservice.infraestructure.credential.gateway.db.repository;

import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import com.harmony.authservice.domain.credential.gateway.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Role;
import com.harmony.authservice.infraestructure.credential.gateway.db.entity.CredentialEntity;
import org.springframework.stereotype.Service;


@Service
public class CredentialDatabaseGateway implements CredentialGateway {

    private final CredentialRepository credentialRepository;

    public CredentialDatabaseGateway(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential findById(Long id) throws CredentialNotFoundException {
        CredentialEntity entity = credentialRepository
                .findById(id)
                .orElseThrow(CredentialNotFoundException::new);

        return new Credential(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                Role.valueOf(entity.getRole())
        );
    }

    @Override
    public Credential findByEmail(String email) throws CredentialNotFoundException {
        CredentialEntity entity = credentialRepository
                .findByEmail(new Email(email))
                .orElseThrow(CredentialNotFoundException::new);

        return new Credential(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                Role.valueOf(entity.getRole())
        );
    }

    @Override
    public Credential save(Credential credential) {
        CredentialEntity entity = credentialRepository.save(new CredentialEntity(
                credential.getEmail().getValue(),
                credential.getPassword().getValue(),
                credential.getRole().name()
        ));

        return new Credential(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                Role.valueOf(entity.getRole())
        );
    }

    @Override
    public void deleteById(Long id) {
        credentialRepository.deleteById(id);
    }

}
