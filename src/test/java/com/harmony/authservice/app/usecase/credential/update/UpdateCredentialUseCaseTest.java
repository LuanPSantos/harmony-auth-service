package com.harmony.authservice.app.usecase.credential.update;

import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.gateway.UpdateCredentialGateway;
import com.harmony.authservice.domain.credential.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static com.harmony.authservice.domain.credential.model.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UpdateCredentialUseCaseTest {

    @Mock
    private UpdateCredentialGateway updateCredentialGateway;
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
        doNothing()
                .when(updateCredentialGateway)
                .update(captor.capture());
        when(credentialQueryGateway.findById(eq(CREDENTIAL_ID)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(USER).build());

        updateCredentialUseCase
                .execute(new UpdateCredentialInput(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(email).build(), rawPassword));

        verify(updateCredentialGateway).update(any());

        Credential credentialCaptured = captor.getValue();
        assertEquals(CREDENTIAL_ID, credentialCaptured.getId());
        assertEquals(email, credentialCaptured.getEmail());
        assertTrue(credentialCaptured.getPassword().matches(RAW_PASSWORD));
        assertEquals(USER, credentialCaptured.getRole());
    }

    @Test
    void ShouldUpdateThePasswordOfCredential() throws Exception {
        Password password = new Password("newPassword");

        ArgumentCaptor<Credential> captor = ArgumentCaptor.forClass(Credential.class);
        doNothing()
                .when(updateCredentialGateway)
                .update(captor.capture());
        when(credentialQueryGateway.findById(eq(CREDENTIAL_ID)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(USER).build());

        updateCredentialUseCase
                .execute(new UpdateCredentialInput(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withRawPassword(password).build(), rawPassword));

        verify(updateCredentialGateway).update(any());

        Credential credentialCaptured = captor.getValue();
        assertEquals(CREDENTIAL_ID, credentialCaptured.getId());
        assertEquals(EMAIL, credentialCaptured.getEmail());
        assertTrue(credentialCaptured.getPassword().matches(password));
        assertEquals(USER, credentialCaptured.getRole());
    }
}
