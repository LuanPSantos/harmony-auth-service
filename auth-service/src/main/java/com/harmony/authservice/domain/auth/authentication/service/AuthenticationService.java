package com.harmony.authservice.domain.auth.authentication.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.model.Subject;
import com.harmony.authservice.domain.userregistration.model.User;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public String authenticate(String username, String password) throws Exception {
        User user = userService.findByEmail(username);

        user.getPassword().checkIfMatches(password);

        Subject subject = new Subject(user.getEmail(), user.getRole());
        return new JWTAuthorization(subject).getToken();
    }
}
