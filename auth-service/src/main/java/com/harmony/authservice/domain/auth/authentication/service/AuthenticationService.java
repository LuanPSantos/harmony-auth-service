package com.harmony.authservice.domain.auth.authentication.service;

import com.harmony.authservice.domain.auth.model.JWTAuthenticatedTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.userregistration.model.User;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.REFRESH_TOKEN_EXPIRATION_TIME;

@Service
public class AuthenticationService {

    private final UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public JWTAuthenticatedTokenPair authenticate(String username, String password) throws Exception {
        User user = userService.findByEmail(username);

        user.getPassword().checkIfMatches(password);

        JWTAuthorization authorization = JWTAuthorization.withSubject(user.getEmail());
        JWTAuthorization refreshAuthorization = JWTAuthorization
                .withSubjectAndExpirationTime(user.getEmail(), REFRESH_TOKEN_EXPIRATION_TIME);

        return new JWTAuthenticatedTokenPair(authorization, refreshAuthorization);
    }
}
