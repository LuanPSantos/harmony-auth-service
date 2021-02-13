package com.harmony.authservice.domain.auth.authorization.controller;

import com.harmony.authservice.domain.auth.authorization.controller.request.AuthorizationRequest;
import com.harmony.authservice.domain.auth.authorization.controller.response.AuthorizationResponse;
import com.harmony.authservice.domain.auth.authorization.service.AuthorizationService;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import org.springframework.web.bind.annotation.*;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.withAuthorizationToken;

@RestController
@RequestMapping("authorizations")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public AuthorizationResponse authorize(@RequestBody AuthorizationRequest request) throws Exception {
        JWTAuthorization jwtAuthorization = authorizationService
                .authorize(withAuthorizationToken(request.getAuthorizationToken()));

        return new AuthorizationResponse(jwtAuthorization.getToken());
    }
}
