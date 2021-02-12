package com.harmony.authservice.domain.auth.controller;

import com.harmony.authservice.domain.auth.controller.request.AuthenticationRequest;
import com.harmony.authservice.domain.auth.controller.response.AuthenticationResponse;
import com.harmony.authservice.domain.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentications")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        String authentication = authService
                .authenticate(request.getUsername(), request.getPassword())
                .getToken();

        return new AuthenticationResponse(authentication);
    }
}
