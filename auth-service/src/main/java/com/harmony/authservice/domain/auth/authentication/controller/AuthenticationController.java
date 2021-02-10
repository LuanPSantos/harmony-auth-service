package com.harmony.authservice.domain.auth.authentication.controller;

import com.harmony.authservice.domain.auth.authentication.controller.request.AuthenticationRequest;
import com.harmony.authservice.domain.auth.authentication.controller.response.AuthenticationResponse;
import com.harmony.authservice.domain.auth.authentication.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentications")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        String authentication = authenticationService.authenticate(request.getUsername(), request.getPassword());

        return new AuthenticationResponse(authentication);
    }
}
