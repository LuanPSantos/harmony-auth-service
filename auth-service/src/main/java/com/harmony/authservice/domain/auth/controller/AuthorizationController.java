package com.harmony.authservice.domain.auth.controller;

import com.harmony.authservice.domain.auth.controller.request.AuthorizationRequest;
import com.harmony.authservice.domain.auth.controller.response.AuthorizationResponse;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authorizations")
public class AuthorizationController {

    private final AuthService authService;

    public AuthorizationController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public AuthorizationResponse authorize(@RequestBody AuthorizationRequest request) throws Exception {
        JWTAuthorization jwtAuthorization = new JWTAuthorization(request.getAuthorization());

        String authorization = authService
                .authorize(jwtAuthorization.getSubject().getUsername())
                .getToken();

        return new AuthorizationResponse(authorization);
    }
}
