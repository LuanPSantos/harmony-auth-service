package com.harmony.authservice.app.domain.credential.model;

import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.model.RawPassword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static com.harmony.authservice.domain.credential.model.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialTest {

    @Test
    void ShouldUpdateTheEmail() {
        Credential credential = new Credential(CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, ADMIN);
        Email newEmail = new Email("new@email.com");

        credential.updateEmail(newEmail);

        Assertions.assertEquals(newEmail, credential.getEmail());
    }

    @Test
    void ShouldNotUpdateTheEmailGivingNullAsNewEmail() {
        Credential credential = new Credential(CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, ADMIN);

        credential.updateEmail(null);

        assertEquals(CREDENTIAL_EMAIL, credential.getEmail());
    }

    @Test
    void ShouldUpdateThePassword() {
        Credential credential = new Credential(CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, ADMIN);
        Password password = new RawPassword("novasenha");

        credential.updatePassword(password);

        assertTrue(credential.getPassword().matches(password));
    }

    @Test
    void ShouldNotUpdateThePasswordGivingNullAsNewPassword() {
        Credential credential = new Credential(CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, ADMIN);

        credential.updatePassword(null);

        assertTrue(credential.getPassword().matches(RAW_PASSWORD));
    }

    @Test
    void ShouldSuccessfullyValidateTheCredential() throws PasswordInvalidException {
        Credential credential = new Credential(CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, ADMIN);

        credential.validate();

        Assertions.assertTrue(true);
    }

    @Test
    void ShouldFailureValidateTheCredential() {
        Credential credential = new Credential(CREDENTIAL_EMAIL, new RawPassword(CREDENTIAL_EMAIL.get()), ADMIN);

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, credential::validate);

        assertEquals(PasswordInvalidException.MESSAGE, exception.getMessage());
    }
}
