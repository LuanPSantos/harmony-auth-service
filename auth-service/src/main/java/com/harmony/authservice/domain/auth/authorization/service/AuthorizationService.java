package com.harmony.authservice.domain.auth.authorization.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.userregistration.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.validateAndCreateFromToken;

@Service
public class AuthorizationService {

    private final UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public JWTAuthorization authorize(String authorizationToken, String refreshAuthorizationToken) throws Exception {
        try {
            JWTAuthorization authorization = validateAndCreateFromToken(authorizationToken);

            checkIfUserExists(authorization.getSubject());

            return authorization;
        }catch (ExpiredJwtException authorizationExpiredException) {
            JWTAuthorization refreshAuthorization = validateAndCreateFromToken(refreshAuthorizationToken);

            return JWTAuthorization.withSubject(refreshAuthorization.getSubject());
        }
    }

    private void checkIfUserExists(String email) {
        userService.findByEmail(email);
    }
}
