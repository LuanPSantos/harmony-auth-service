package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Role;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.validateAuthorizationToken;

@Service
public class AuthorizationService {

    @Value("${auth.authorization-token.ttl}")
    private Long authorizationTokenTimeToLive;

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
                        refreshAuthorization.getSubject(),
                        authorizationTokenTimeToLive,
                        refreshAuthorization.getRole());
            }

            throw new ForbiddenException();
        }
    }
}
