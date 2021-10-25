package com.harmony.authservice.app.utils;

import com.harmony.authservice.domain.credential.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.harmony.authservice.domain.credential.model.Role.ADMIN;

public interface CredentialTestConstants {

    Email EMAIL = new Email("credential@email.com");
    CredentialId CREDENTIAL_ID = new CredentialId(1L);
    Password RAW_PASSWORD = new Password("password");
    EncodedPassword ENCODED_PASSWORD = EncodedPassword.fromRawPassword(RAW_PASSWORD.get());
}
