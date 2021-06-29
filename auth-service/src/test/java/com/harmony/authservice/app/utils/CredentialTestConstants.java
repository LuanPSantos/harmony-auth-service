package com.harmony.authservice.app.utils;

import com.harmony.authservice.domain.credential.model.CredentialId;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface CredentialTestConstants {

    Email CREDENTIAL_EMAIL = new Email("credential@email.com");
    CredentialId CREDENTIAL_ID = new CredentialId(1L);
    String RAW_PASSWORD = "password";
    Password CREDENTIAL_PASSWORD = new Password(new BCryptPasswordEncoder().encode(RAW_PASSWORD));
}
