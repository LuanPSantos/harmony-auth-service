package com.harmony.authservice.domain.auth.authorization.controller;

import com.harmony.authservice.domain.auth.authorization.controller.request.AuthorizationRequest;
import com.harmony.authservice.domain.auth.authorization.controller.response.AuthorizationResponse;
import com.harmony.authservice.domain.auth.authorization.service.AuthorizationService;
import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair.REFRESH_AUTHENTICATION_COOKIE_KEY;

@RestController
@RequestMapping("authorizations")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<Void> authorize(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationToken,
            @RequestParam("required-role") Role role,
            @CookieValue(REFRESH_AUTHENTICATION_COOKIE_KEY) String refreshAuthentication) throws ForbiddenException {

        System.out.println("Tentando autorizar");
        JWTAuthorization jwtAuthorization = authorizationService
                .authorize(
                        authorizationToken,
                        new JWTAuthorization(refreshAuthentication).getAuthorizationToken(),
                        role);

        System.out.println("Autorizado!");

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, jwtAuthorization.getAuthorizationToken())
                .build();
    }
}
