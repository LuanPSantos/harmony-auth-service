package com.harmony.authservice.app.utils;

import com.harmony.authservice.domain.credential.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface CredentialTestConstants {

    Email CREDENTIAL_EMAIL = new Email("credential@email.com");
    CredentialId CREDENTIAL_ID = new CredentialId(1L);
    RawPassword RAW_PASSWORD = new RawPassword("password");
    Password CREDENTIAL_PASSWORD = new EncodedPassword(new BCryptPasswordEncoder().encode(RAW_PASSWORD.get()));
}
