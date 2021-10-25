package com.harmony.authservice.app.domain.credential.model;

import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
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
        Password password = new Password("novasenha");

        credential.updatePassword(password);

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

    @Test
    void ShouldSuccessfullyValidateTheCredential() throws PasswordInvalidException {
        Credential credential = new Credential.Builder()
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(ADMIN).build();

        credential.validate();

        Assertions.assertTrue(true);
    }

    @Test
    void ShouldFailureValidateTheCredential() {
        Credential credential = new Credential.Builder()
                .withEmail(EMAIL)
                .withRawPassword(EMAIL.get())
                .withRole(ADMIN).build();

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, credential::validate);

        assertEquals(PasswordInvalidException.MESSAGE, exception.getMessage());
    }
}
