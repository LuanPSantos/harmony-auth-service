package com.harmony.authservice.domain.auth.authorization.service;

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

        checkIfAuthorizationIsValid(authorization);
        checkIfUserExists(authorization.getSubject());

        return authorization;
    }

    private void checkIfUserExists(String email) {
        userService.findByEmail(email);
    }



    private void checkIfAuthorizationIsValid(JWTAuthorization authorization) throws Exception {
        if(!authorization.isValid()) {
            throw new Exception("Autorização invalida");
        }
    }
}
