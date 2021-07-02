package com.harmony.authservice.app.usecase.credential.update;

import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.gateway.UpdateCredentialGateway;
import com.harmony.authservice.domain.credential.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static com.harmony.authservice.domain.credential.model.Role.ADMIN;
import static com.harmony.authservice.domain.credential.model.Role.USER;
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
                .thenReturn(new Credential(CREDENTIAL_ID, CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, USER));

        updateCredentialUseCase
                .execute(new UpdateCredentialInput(new Credential(CREDENTIAL_ID, email, null)));

        verify(updateCredentialGateway).update(any());

        Credential credentialCaptured = captor.getValue();
        Assertions.assertEquals(CREDENTIAL_ID, credentialCaptured.getId());
        Assertions.assertEquals(email, credentialCaptured.getEmail());
        Assertions.assertTrue(credentialCaptured.getPassword().matches(RAW_PASSWORD));
        Assertions.assertEquals(USER, credentialCaptured.getRole());
    }

    @Test
    void ShouldUpdateThePasswordOfCredential() throws Exception {
        Password password = new RawPassword("newPassword");

        ArgumentCaptor<Credential> captor = ArgumentCaptor.forClass(Credential.class);
        doNothing()
                .when(updateCredentialGateway)
                .update(captor.capture());
        when(credentialQueryGateway.findById(eq(CREDENTIAL_ID)))
                .thenReturn(new Credential(CREDENTIAL_ID, CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, USER));

        updateCredentialUseCase
                .execute(new UpdateCredentialInput(new Credential(CREDENTIAL_ID, null, password)));

        verify(updateCredentialGateway).update(any());

        Credential credentialCaptured = captor.getValue();
        Assertions.assertEquals(CREDENTIAL_ID, credentialCaptured.getId());
        Assertions.assertEquals(CREDENTIAL_EMAIL, credentialCaptured.getEmail());
        Assertions.assertTrue(credentialCaptured.getPassword().matches(password));
        Assertions.assertEquals(USER, credentialCaptured.getRole());
    }
}
