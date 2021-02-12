package com.harmony.userregistration.credential.repository;

import com.harmony.userregistration.credential.model.Credential;
import com.harmony.userregistration.credential.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    @Query("SELECT credential FROM Credential credential " +
            "WHERE credential.email = :email")
    Optional<Credential> findByEmail(Email email);

}
