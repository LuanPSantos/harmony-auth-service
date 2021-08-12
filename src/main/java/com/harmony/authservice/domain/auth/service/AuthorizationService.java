package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Role;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.fromAuthorizationToken;

@Component
public class AuthorizationService {

    private final Long authorizationTokenTimeToLive;

    public AuthorizationService(
            @Value("${auth.authorization-token.ttl}")
            Long authorizationTokenTimeToLive) {
        this.authorizationTokenTimeToLive = authorizationTokenTimeToLive;
    }

    public JWTAuthorization authorize(String authorizationToken, String refreshAuthorizationToken, Role roleRequired) throws ForbiddenException {
        try {
            JWTAuthorization jwtAuthorization = fromAuthorizationToken(authorizationToken);

            if (jwtAuthorization.getRole() == roleRequired) {
                return jwtAuthorization;
            }

            throw new ForbiddenException();
        } catch (ExpiredJwtException authorizationExpiredException) {
            JWTAuthorization refreshAuthorization = fromAuthorizationToken(refreshAuthorizationToken);

            if (refreshAuthorization.getRole() == roleRequired) {
                return new JWTAuthorization(
                        refreshAuthorization.getEmail(),
                        authorizationTokenTimeToLive,
                        refreshAuthorization.getRole());
            }

            throw new ForbiddenException();
        }
    }
}
