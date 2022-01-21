package com.harmony.authservice.app.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.RawPassword;
import com.harmony.authservice.domain.credential.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.harmony.authservice.app.utils.AuthorizationTestConstants.TTL;
import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static com.harmony.authservice.app.utils.CredentialTestConstants.ENCODED_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(TTL, TTL);
    }

    @Test
    void ShouldAuthenticateSuccessfully() throws Exception {
        Credential credential = new Credential.Builder()
                .withId(CREDENTIAL_ID)
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(Role.USER).build();

        JWTAuthorizationTokenPair pair = authenticationService.authenticate(credential, RAW_PASSWORD);

        assertNotNull(pair.getAuthorization());
        assertNotNull(pair.getRefreshAuthorization());
    }

    @Test
    void ShouldFailAuthenticateDueWrongPassword() {
        Credential credential = new Credential.Builder()
                .withId(CREDENTIAL_ID)
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(Role.USER).build();

        AuthenticationException exception = assertThrows(AuthenticationException.class,
                () -> authenticationService.authenticate(credential, new RawPassword("wrongPassword")));

        assertEquals(AuthenticationException.MESSAGE, exception.getMessage());
    }
}
