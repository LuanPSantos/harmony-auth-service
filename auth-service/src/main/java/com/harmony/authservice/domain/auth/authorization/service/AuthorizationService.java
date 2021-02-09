package com.harmony.authservice.domain.auth.authorization.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.model.Subject;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public String authorize(JWTAuthorization authorization) throws Exception {

        checkIfIsExpired(authorization);
        Subject subject = authorization.getSubject();
        checkIfUserExists(subject.getUsername());

        return new JWTAuthorization(subject).getToken();
    }

    private void checkIfUserExists(String email) {
        userService.findByEmail(email);
    }

    private void checkIfIsExpired(JWTAuthorization authorization) throws Exception {
        if (authorization.isExpired()) {
            throw new Exception("Token Expirado!");
        }
    }
}
