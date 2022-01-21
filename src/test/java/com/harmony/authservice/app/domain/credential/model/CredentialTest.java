package com.harmony.authservice.app.domain.credential.model;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.EncodedPassword;
import com.harmony.authservice.domain.credential.model.RawPassword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static com.harmony.authservice.domain.credential.model.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialTest {

    @Test
    void ShouldUpdateTheEmail() {
        Credential credential = new Credential.Builder()
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(ADMIN).build();
        Email newEmail = new Email("new@email.com");

        credential.updateEmail(newEmail);

        Assertions.assertEquals(newEmail, credential.getEmail());
    }

    @Test
    void ShouldNotUpdateTheEmailGivingNullAsNewEmail() {
        Credential credential = new Credential.Builder()
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(ADMIN).build();

        credential.updateEmail(null);

        assertEquals(EMAIL, credential.getEmail());
    }

    @Test
    void ShouldUpdateThePassword() {
        Credential credential = new Credential.Builder()
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(ADMIN).build();
        RawPassword password = new RawPassword("novasenha");

        credential.updatePassword(EncodedPassword.encodeRawPassword(password));

        assertTrue(credential.getPassword().matches(password));
    }

    @Test
    void ShouldNotUpdateThePasswordGivingNullAsNewPassword() {
        Credential credential = new Credential.Builder()
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(ADMIN).build();

        credential.updatePassword(null);

        assertTrue(credential.getPassword().matches(RAW_PASSWORD));
    }
}
