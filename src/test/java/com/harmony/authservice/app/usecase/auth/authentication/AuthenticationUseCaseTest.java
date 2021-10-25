package com.harmony.authservice.app.usecase.auth.authentication;

import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationInput;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationOutput;
import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.harmony.authservice.app.utils.AuthorizationTestConstants.*;
import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthenticationUseCaseTest {

    @Mock
    private CredentialQueryGateway credentialQueryGateway;
    @Mock
    private AuthenticationService authenticationService;

    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authenticationUseCase = new AuthenticationUseCase(
                new AuthenticationService(TTL, TTL),
                credentialQueryGateway);
    }

    @Test
    void ShouldAuthenticateAnUserWithValidCredential() throws Exception {
        Credential credential = new Credential.Builder()
                .withId(CREDENTIAL_ID)
                .withEmail(EMAIL)
                .withEncodedPassword(ENCODED_PASSWORD)
                .withRole(Role.USER).build();

        when(credentialQueryGateway.findByEmail(eq(EMAIL)))
                .thenReturn(credential);
        when(authenticationService.authenticate(eq(credential), eq(RAW_PASSWORD)))
                .thenReturn(new JWTAuthorizationTokenPair(AUTHORIZATION_TOKEN, REFRESH_AUTHORIZATION_TOKEN));

        AuthenticationOutput output = authenticationUseCase.execute(new AuthenticationInput(EMAIL, RAW_PASSWORD));

        String emailInAuthorizationTokenSubject = output.getJwtAuthorizationTokenPair().getAuthorization().getEmail();
        Role emailInAuthorizationTokenRole = output.getJwtAuthorizationTokenPair().getAuthorization().getRole();

        String emailInRefreshAuthorizationTokenSubject = output.getJwtAuthorizationTokenPair().getRefreshAuthorization().getEmail();
        Role emailInRefreshAuthorizationTokenRole = output.getJwtAuthorizationTokenPair().getRefreshAuthorization().getRole();

        assertEquals(EMAIL.get(), emailInAuthorizationTokenSubject);
        assertEquals(Role.USER, emailInAuthorizationTokenRole);

        assertEquals(EMAIL.get(), emailInRefreshAuthorizationTokenSubject);
        assertEquals(Role.USER, emailInRefreshAuthorizationTokenRole);
    }

    @Test
    void ShouldFailAuthenticationDuneInvalidEmail() throws CredentialNotFoundException {
        Email nonExistentEmail = new Email("nonExistentEmail@email.com");

        when(credentialQueryGateway.findByEmail(eq(nonExistentEmail)))
                .thenThrow(CredentialNotFoundException.class);

        AuthenticationException exception = assertThrows(AuthenticationException.class,
                () -> authenticationUseCase.execute(new AuthenticationInput(nonExistentEmail, RAW_PASSWORD)));

        assertEquals(AuthenticationException.MESSAGE, exception.getMessage());
    }
}
