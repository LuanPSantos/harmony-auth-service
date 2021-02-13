package com.harmony.authservice.domain.auth.authorization.service;

import com.harmony.authservice.domain.auth.authorization.exception.AuthorizationExpiredException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public JWTAuthorization authorize(JWTAuthorization authorization) throws Exception {

        try {
            checkIfAuthorizationIsNotExpired(authorization);
            checkIfUserExists(authorization.getSubject());

            return authorization;
        }catch (AuthorizationExpiredException authorizationExpiredException) {
            //TODO tentar usar o refresh token;
            return null;
        }
    }

    private void checkIfUserExists(String email) {
        userService.findByEmail(email);
    }

    private void checkIfAuthorizationIsNotExpired(JWTAuthorization authorization) throws AuthorizationExpiredException {
        if (authorization.isExpired()) {
            throw new AuthorizationExpiredException();
        }
    }
}
