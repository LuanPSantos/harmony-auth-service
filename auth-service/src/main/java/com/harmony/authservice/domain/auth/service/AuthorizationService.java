package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Role;
import io.jsonwebtoken.ExpiredJwtException;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.validateAuthorizationToken;

public class AuthorizationService {

    private final Long authorizationTokenTimeToLive;

    public AuthorizationService(Long authorizationTokenTimeToLive) {
        this.authorizationTokenTimeToLive = authorizationTokenTimeToLive;
    }

    public JWTAuthorization authorize(String authorizationToken, String refreshAuthorizationToken, Role roleRequired) throws ForbiddenException {
        try {
            JWTAuthorization jwtAuthorization = validateAuthorizationToken(authorizationToken);

            if (jwtAuthorization.getRole() == roleRequired) {
                return jwtAuthorization;
            }

            throw new ForbiddenException();
        } catch (ExpiredJwtException authorizationExpiredException) {
            JWTAuthorization refreshAuthorization = validateAuthorizationToken(refreshAuthorizationToken);

            if (refreshAuthorization.getRole() == roleRequired) {
                return JWTAuthorization.withEmailAndExpirationTimeAndRole(
                        refreshAuthorization.getEmail(),
                        authorizationTokenTimeToLive,
                        refreshAuthorization.getRole());
            }

            throw new ForbiddenException();
        }
    }
}
