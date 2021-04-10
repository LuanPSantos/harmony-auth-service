package com.harmony.authservice.infraestructure.credential.gateway.db.repository;

import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.infraestructure.credential.gateway.db.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    @Query("SELECT credential FROM Credential credential " +
            "WHERE credential.email = :email")
    Optional<CredentialEntity> findByEmail(Email email);

}
