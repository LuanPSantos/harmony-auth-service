package com.harmony.authservice.domain.auth.authorization.service;

import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Role;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.validateAuthorizationToken;

@Service
public class AuthorizationService {

    public JWTAuthorization authorize(String authorizationToken, String refreshAuthorizationToken, Role roleRequiredByEndpoint) throws ForbiddenException{
        try {
            JWTAuthorization jwtAuthorization = validateAuthorizationToken(authorizationToken);

            if(jwtAuthorization.getRole() == roleRequiredByEndpoint) {
                return jwtAuthorization;
            }

            throw new ForbiddenException();
        }catch (ExpiredJwtException authorizationExpiredException) {
            JWTAuthorization refreshAuthorization = validateAuthorizationToken(refreshAuthorizationToken);

            if(refreshAuthorization.getRole() == roleRequiredByEndpoint) {
                return JWTAuthorization.withEmailAndRole(refreshAuthorization.getSubject(), refreshAuthorization.getRole());
            }

            throw new ForbiddenException();
        }
    }
}
