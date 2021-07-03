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
        when(credentialQueryGateway.findByEmail(eq(EMAIL)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(Role.USER).build());

        FindCredentialByEmailOutput output = findCredentialByEmailUseCase.execute(new FindCredentialByEmailInput(EMAIL));

        assertEquals(CREDENTIAL_ID, output.getCredential().getId());
        assertEquals(EMAIL, output.getCredential().getEmail());
        assertEquals(Role.USER, output.getCredential().getRole());
        assertEquals(ENCODED_PASSWORD, output.getCredential().getPassword());
    }
}
