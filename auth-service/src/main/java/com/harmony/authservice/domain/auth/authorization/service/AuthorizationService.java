package com.harmony.authservice.domain.auth.authorization.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.validateAuthorizationToken;

@Service
public class AuthorizationService {

    public JWTAuthorization authorize(String authorizationToken, String refreshAuthorizationToken) {
        try {
            return validateAuthorizationToken(authorizationToken);
        }catch (ExpiredJwtException authorizationExpiredException) {
            JWTAuthorization refreshAuthorization = validateAuthorizationToken(refreshAuthorizationToken);

            return JWTAuthorization.withSubject(refreshAuthorization.getSubject());
        }
    }
}
