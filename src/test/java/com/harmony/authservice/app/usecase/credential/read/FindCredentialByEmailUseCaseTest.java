package com.harmony.authservice.app.usecase.credential.read;

import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailInput;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailOutput;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FindCredentialByEmailUseCaseTest {
    @Mock
    private CredentialQueryGateway credentialQueryGateway;

    @InjectMocks
    private FindCredentialByEmailUseCase findCredentialByEmailUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldFindACredentialByEmail() throws Exception {
        when(credentialQueryGateway.findByEmail(eq(CREDENTIAL_EMAIL)))
                .thenReturn(new Credential(CREDENTIAL_ID, CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, Role.USER));

        FindCredentialByEmailOutput output = findCredentialByEmailUseCase.execute(new FindCredentialByEmailInput(CREDENTIAL_EMAIL));

        assertEquals(CREDENTIAL_ID, output.getCredential().getId());
        assertEquals(CREDENTIAL_EMAIL, output.getCredential().getEmail());
        assertEquals(Role.USER, output.getCredential().getRole());
        assertEquals(CREDENTIAL_PASSWORD, output.getCredential().getPassword());
    }
}
