package com.harmony.authservice.app.usecase.auth.authorization;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

public class AuthenticationServiceTest {

    @Mock
    private CredentialQueryGateway credentialQueryGateway;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authenticationService = new AuthenticationService(credentialQueryGateway, TTL, TTL);
    }

    @Test
    void ShouldAuthenticateAnUser() throws Exception {

        Mockito.when(credentialQueryGateway.findByEmail(eq(EMAIL)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(Role.USER).build());

        JWTAuthorizationTokenPair jwtAuthorizationTokenPair = authenticationService.authenticate(EMAIL, RAW_PASSWORD);

        assertNotNull(jwtAuthorizationTokenPair.getAuthorization());
        assertNotNull(jwtAuthorizationTokenPair.getRefreshAuthorization());
    }

    @Test
    void ShouldNotAuthenticateDueWrongEmail() throws Exception {

        Mockito.when(credentialQueryGateway.findByEmail(eq(EMAIL)))
                .thenThrow(CredentialNotFoundException.class);

        Assertions.assertThrows(AuthenticationException.class,
                () -> authenticationService.authenticate(EMAIL, RAW_PASSWORD));
    }

    @Test
    void ShouldNotAuthenticateDueWrongPassword() throws Exception {

        Mockito.when(credentialQueryGateway.findByEmail(eq(EMAIL)))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(Role.USER).build());

        Assertions.assertThrows(AuthenticationException.class,
                () -> authenticationService.authenticate(EMAIL, new Password("wrong-password")));
    }
}
