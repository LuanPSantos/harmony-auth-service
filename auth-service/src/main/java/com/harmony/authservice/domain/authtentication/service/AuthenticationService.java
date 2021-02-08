package com.harmony.authservice.domain.authtentication.service;

import com.harmony.authservice.domain.userregistration.model.User;
import com.harmony.authservice.domain.userregistration.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public String authenticate(String username, String password) throws Exception {
        User user = userService.findByEmail(username);

        user.getPassword().checkMatches(password);

        return "token";
    }
}
