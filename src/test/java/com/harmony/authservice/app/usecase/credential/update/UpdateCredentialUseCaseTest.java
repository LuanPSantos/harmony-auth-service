package com.harmony.authservice.app.usecase.credential.update;

import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialOutput;
import com.harmony.authservice.domain.credential.exception.PasswordDidNotMatchException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.gateway.SaveCredentialGateway;
import com.harmony.authservice.domain.credential.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static com.harmony.authservice.domain.credential.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UpdateCredentialUseCaseTest {

    @Mock
    private SaveCredentialGateway saveCredentialGateway;
    @Mock
    private CredentialQueryGateway credentialQueryGateway;

    @InjectMocks
    private UpdateCredentialUseCase updateCredentialUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldUpdateTheEmailOfCredential() throws Exception {
        Email email = new Email("abc@email");

        ArgumentCaptor<Credential> captor = ArgumentCaptor.forClass(Credential.class);
        when(saveCredentialGateway.save(captor.capture()))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(email)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(USER).build());
        when(credentialQueryGateway.findById(eq(CREDENTIAL_ID)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(USER).build());

        UpdateCredentialOutput output = updateCredentialUseCase
                .execute(new UpdateCredentialInput(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(email).build(), RAW_PASSWORD));

        verify(saveCredentialGateway).save(any());

        Credential credentialCaptured = captor.getValue();
        assertEquals(CREDENTIAL_ID, credentialCaptured.getId());
        assertEquals(email, credentialCaptured.getEmail());
        assertTrue(credentialCaptured.getPassword().matches(RAW_PASSWORD));
        assertEquals(USER, credentialCaptured.getRole());

        Credential returnedCredential = output.getCredential();
        assertEquals(CREDENTIAL_ID, returnedCredential.getId());
        assertEquals(email, returnedCredential.getEmail());
        assertTrue(returnedCredential.getPassword().matches(RAW_PASSWORD));
        assertEquals(USER, returnedCredential.getRole());
    }

    @Test
    void ShouldUpdateThePasswordOfCredential() throws Exception {
        Password password = new Password("newPassword");

        ArgumentCaptor<Credential> captor = ArgumentCaptor.forClass(Credential.class);
        when(saveCredentialGateway.save(captor.capture()))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(EncodedPassword.fromRawPassword(password.get()))
                        .withRole(USER).build());
        when(credentialQueryGateway.findById(eq(CREDENTIAL_ID)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(USER).build());

        UpdateCredentialOutput output = updateCredentialUseCase
                .execute(new UpdateCredentialInput(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withRawPassword(password).build(), RAW_PASSWORD));

        verify(saveCredentialGateway).save(any());

        Credential credentialCaptured = captor.getValue();
        assertEquals(CREDENTIAL_ID, credentialCaptured.getId());
        assertEquals(EMAIL, credentialCaptured.getEmail());
        assertTrue(credentialCaptured.getPassword().matches(password));
        assertEquals(USER, credentialCaptured.getRole());

        Credential returnedCredential = output.getCredential();
        assertEquals(CREDENTIAL_ID, returnedCredential.getId());
        assertEquals(EMAIL, returnedCredential.getEmail());
        assertTrue(returnedCredential.getPassword().matches(password));
        assertEquals(USER, returnedCredential.getRole());
    }

    @Test
    void ShouldNotUpdateCredentialWhenCurrentPasswordIsWrong() throws Exception {
        Password password = new Password("newPassword");

        when(credentialQueryGateway.findById(eq(CREDENTIAL_ID)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(USER).build());

        assertThrows(PasswordDidNotMatchException.class, () -> {
            updateCredentialUseCase
                    .execute(new UpdateCredentialInput(new Credential.Builder()
                            .withId(CREDENTIAL_ID)
                            .withRawPassword(password).build(), password));
        });

        verify(saveCredentialGateway, times(0)).save(any());
    }
}
