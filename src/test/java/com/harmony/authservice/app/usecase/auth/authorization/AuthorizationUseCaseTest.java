package com.harmony.authservice.app.usecase.auth.authorization;

import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationInput;
import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationOutput;
import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Email;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.withEmailAndExpirationTimeAndRole;
import static com.harmony.authservice.domain.credential.model.Role.ADMIN;
import static com.harmony.authservice.domain.credential.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationUseCaseTest {

    private static final Email CREDENTIAL_EMAIL = new Email("credential@email.com");
    private static final Long TTL = 1000L;

    private AuthorizationUseCase authorizationUseCase;

    @BeforeEach
    void setUp() {
        authorizationUseCase = new AuthorizationUseCase(TTL * 2);
    }

    @Test
    void ShouldAuthorizeUsingAuthorizationToken() throws Exception {
        JWTAuthorization authorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL * 2,
                USER
        );

        AuthorizationOutput output = authorizationUseCase
                .execute(new AuthorizationInput(authorization.asToken(), refreshAuthorization.asToken(), USER));

        assertEquals(authorization, output.getJwtAuthorization());
    }

    @Test
    void ShouldAuthorizeUsingRefreshAuthorizationToken() throws Exception {
        JWTAuthorization authorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL * 2,
                USER
        );

        Thread.sleep(TTL + 1);

        AuthorizationOutput output = authorizationUseCase
                .execute(new AuthorizationInput(authorization.asToken(), refreshAuthorization.asToken(), USER));

        assertNotEquals(authorization, output.getJwtAuthorization());
    }

    @Test
    void ShouldNotAuthorizeDueExpirationTime() throws Exception {
        JWTAuthorization authorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL,
                USER
        );

        Thread.sleep(TTL + 1L);

        assertThrows(ExpiredJwtException.class, () -> authorizationUseCase
                .execute(new AuthorizationInput(authorization.asToken(), refreshAuthorization.asToken(), USER)));
    }

    @Test
    void ShouldNotAuthorizeUserWithWrongRole() {
        JWTAuthorization authorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = withEmailAndExpirationTimeAndRole(
                CREDENTIAL_EMAIL.get(),
                TTL,
                USER
        );

        assertThrows(ForbiddenException.class, () -> authorizationUseCase
                .execute(new AuthorizationInput(authorization.asToken(), refreshAuthorization.asToken(), ADMIN)));
    }
}
