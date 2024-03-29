package com.harmony.authservice.app.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.service.AuthorizationService;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;

import static com.harmony.authservice.app.utils.AuthorizationTestConstants.TTL;
import static com.harmony.authservice.app.utils.CredentialTestConstants.EMAIL;
import static com.harmony.authservice.domain.credential.model.Role.ADMIN;
import static com.harmony.authservice.domain.credential.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {

    private final AuthorizationService authorizationService = new AuthorizationService(TTL);

    @Test
    void ShouldAuthorizeWithAuthorizationToken() throws Exception {
        JWTAuthorization authorization = new JWTAuthorization(
                EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = new JWTAuthorization(
                EMAIL.get(),
                TTL * 2,
                USER
        );

        JWTAuthorization jwtAuthorization = authorizationService
                .authorize(authorization.asToken(), refreshAuthorization.asToken(), USER);


        assertEquals(authorization.asToken(), jwtAuthorization.asToken());
    }

    @Test
    void ShouldAuthorizeWithRefreshAuthorizationToken() throws Exception {
        JWTAuthorization authorization = new JWTAuthorization(
                EMAIL.get(),
                TTL,
                USER
        );
        Thread.sleep(TTL);
        JWTAuthorization refreshAuthorization = new JWTAuthorization(
                EMAIL.get(),
                TTL * 2,
                USER
        );

        JWTAuthorization jwtAuthorization = authorizationService
                .authorize(authorization.asToken(), refreshAuthorization.asToken(), USER);


        assertNotEquals(authorization.asToken(), jwtAuthorization.asToken());
    }

    @Test
    void ShouldNotAuthorizeDueRefreshAuthorizationExpired() throws InterruptedException {
        JWTAuthorization authorization = new JWTAuthorization(
                EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = new JWTAuthorization(
                EMAIL.get(),
                TTL,
                USER
        );
        Thread.sleep(TTL);

        assertThrows(ExpiredJwtException.class, () -> authorizationService
                .authorize(authorization.asToken(), refreshAuthorization.asToken(), USER));
    }

    @Test
    void ShouldNotAuthorizeDueWrongRole() {
        JWTAuthorization authorization = new JWTAuthorization(
                EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = new JWTAuthorization(
                EMAIL.get(),
                TTL * 2,
                USER
        );

        assertThrows(ForbiddenException.class, () -> authorizationService
                .authorize(authorization.asToken(), refreshAuthorization.asToken(), ADMIN));
    }
}
