package com.harmony.authservice.domain.authorization.service;

import com.harmony.authservice.common.jwt.JWTUtils;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.harmony.authservice.common.jwt.JWTUtils.*;

@Service
public class AuthorizationService {

    private final UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public String authorize(String authorization) throws Exception {

        checkIfAuthorizationIsExpired(authorization);

        // TODO subject as a class
        String emailAsUsername = extractSubjectFromAuthorization(authorization);
        checkIfUserExists(emailAsUsername);

        return generateAuthorization(emailAsUsername);
    }

    private void checkIfUserExists(String email) {
        userService.findByEmail(email);
    }

    private void checkIfAuthorizationIsExpired(String authorization) throws Exception {
        Date expiration = extractExpirationFromAuthorization(authorization);

        if(expiration.before(new Date())) {
            throw new Exception("Não autorizado");
        }
    }
}
