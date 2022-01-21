package com.harmony.authservice.app.utils;

import com.harmony.authservice.domain.credential.model.*;

public interface CredentialTestConstants {

    Email EMAIL = new Email("credential@email.com");
    CredentialId CREDENTIAL_ID = new CredentialId(1L);
    RawPassword RAW_PASSWORD = new RawPassword("password");
    EncodedPassword ENCODED_PASSWORD = EncodedPassword.encodeRawPassword(RAW_PASSWORD);
}
