package com.harmony.authservice.app.domain.auth.model;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.harmony.authservice.domain.credential.model.Role.USER;

public class JWTAuthorizationTest {

    private final String EMAIL = "EMAIL";
    private final Long TTL = 5000L;

    @Test
    void ShouldWithEmailAndExpirationTimeAndRole() {

        JWTAuthorization authorization = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL, TTL, USER);

        Assertions.assertNotNull(authorization);
    }

    @Test
    void ShouldValidateAuthorizationToken() {
        String token = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL, TTL, USER).asToken();

        JWTAuthorization authorization = JWTAuthorization.validateAuthorizationToken(token);

        Assertions.assertNotNull(authorization);
    }

    @Test
    void ShouldGetEmailFromAuthorizationToken() {
        JWTAuthorization authorization = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL, TTL, USER);

        Assertions.assertEquals(EMAIL, authorization.getEmail());
    }

    @Test
    void ShouldGetRoleFromAuthorizationToken() {
        JWTAuthorization authorization = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL, TTL, USER);

        Assertions.assertEquals(USER, authorization.getRole());
    }

    @Test
    void ShouldGetAuthorizationAsToken() {
        JWTAuthorization authorization = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL, TTL, USER);

        Assertions.assertTrue(authorization.asToken().startsWith("Bearer"));
    }
}
