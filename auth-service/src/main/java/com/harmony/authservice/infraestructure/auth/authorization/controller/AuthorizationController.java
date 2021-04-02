package com.harmony.authservice.infraestructure.auth.authorization.controller;

import com.harmony.authservice.domain.auth.authorization.usecase.io.AuthorizationInput;
import com.harmony.authservice.domain.auth.authorization.usecase.io.AuthorizationOutput;
import com.harmony.authservice.domain.credential.model.Role;
import com.harmony.authservice.domain.usecase.UseCase;
import com.harmony.authservice.infraestructure.auth.authentication.controller.AuthenticationController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.harmony.authservice.infraestructure.auth.authentication.controller.AuthenticationController.REFRESH_AUTHORIZATION_COOKIE_KEY;


@RestController
@RequestMapping("authorizations")
public class AuthorizationController {

    private final String REQUIRED_ROLE_REQUEST_PARAM_NAME = "required-role";

    @Qualifier("authorizationUseCase")
    private final UseCase<AuthorizationInput, AuthorizationOutput> authorizationUseCase;

    public AuthorizationController(UseCase<AuthorizationInput, AuthorizationOutput> authorizationUseCase) {
        this.authorizationUseCase = authorizationUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> authorize(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationToken,
            @RequestParam(REQUIRED_ROLE_REQUEST_PARAM_NAME) Role requiredRole,
            @CookieValue(REFRESH_AUTHORIZATION_COOKIE_KEY) String refreshAuthentication) throws Exception {

        AuthorizationOutput output = authorizationUseCase.execute(new AuthorizationInput(
                authorizationToken,
                refreshAuthentication,
                requiredRole)
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, output.getAuthorizationToken())
                .build();
    }
}
