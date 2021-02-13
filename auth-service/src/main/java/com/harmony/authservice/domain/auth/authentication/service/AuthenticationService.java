package com.harmony.authservice.domain.auth.authentication.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.userregistration.model.User;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public JWTAuthorization authenticate(String username, String password) throws Exception {
        User user = userService.findByEmail(username);

        user.getPassword().checkIfMatches(password);

        return JWTAuthorization.withSubject(user.getEmail());
    }
}
