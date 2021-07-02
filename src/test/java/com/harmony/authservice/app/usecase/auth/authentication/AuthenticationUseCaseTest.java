package com.harmony.authservice.app.usecase.auth.authentication;

import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationInput;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationOutput;
import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthenticationUseCaseTest {


    @Mock
    private CredentialQueryGateway credentialQueryGateway;

    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Long authorizationTokenTTL = 2000L;
        Long refreshAuthorizationTokenTTL = 2000L;

        authenticationUseCase = new AuthenticationUseCase(
                new AuthenticationService(
                        credentialQueryGateway,
                        authorizationTokenTTL,
                        refreshAuthorizationTokenTTL
                )
        );
    }

    @Test
    void ShouldAuthenticateAnUserWithValidCredential() throws Exception {

        when(credentialQueryGateway.findByEmail(eq(CREDENTIAL_EMAIL)))
                .thenReturn(new Credential(CREDENTIAL_ID, CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, Role.USER));

        AuthenticationOutput output = authenticationUseCase.execute(new AuthenticationInput(CREDENTIAL_EMAIL, RAW_PASSWORD));

        String emailInAuthorizationTokenSubject = output.getJwtAuthorizationTokenPair().getAuthorization().getEmail();
        Role emailInAuthorizationTokenRole = output.getJwtAuthorizationTokenPair().getAuthorization().getRole();

        String emailInRefreshAuthorizationTokenSubject = output.getJwtAuthorizationTokenPair().getRefreshAuthorization().getEmail();
        Role emailInRefreshAuthorizationTokenRole = output.getJwtAuthorizationTokenPair().getRefreshAuthorization().getRole();

        assertEquals(CREDENTIAL_EMAIL.get(), emailInAuthorizationTokenSubject);
        assertEquals(Role.USER, emailInAuthorizationTokenRole);

        assertEquals(CREDENTIAL_EMAIL.get(), emailInRefreshAuthorizationTokenSubject);
        assertEquals(Role.USER, emailInRefreshAuthorizationTokenRole);
    }

    @Test
    void ShouldFailAuthenticationDueInvalidPassword() throws CredentialNotFoundException {
        RawPassword wrongRawPassword = new RawPassword("wrongRawPassword");

        when(credentialQueryGateway.findByEmail(eq(CREDENTIAL_EMAIL)))
                .thenReturn(new Credential(CREDENTIAL_ID, CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, Role.USER));

        AuthenticationException exception = assertThrows(AuthenticationException.class,
                () -> authenticationUseCase.execute(new AuthenticationInput(CREDENTIAL_EMAIL, wrongRawPassword)));

        assertEquals(AuthenticationException.MESSAGE, exception.getMessage());
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
