package com.harmony.authservice.infraestructure.auth.authentication.controller;

import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationInput;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationOutput;
import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.infraestructure.auth.authentication.controller.request.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("authentications")
public class AuthenticationController {

    public static final String REFRESH_AUTHORIZATION_COOKIE_KEY = "refresh-authorization";

    @Qualifier("authenticationUseCase")
    private final UseCase<AuthenticationInput, AuthenticationOutput> authenticationUseCase;

    public AuthenticationController(UseCase<AuthenticationInput, AuthenticationOutput> authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response) throws Exception {

        AuthenticationOutput output = authenticationUseCase.execute(new AuthenticationInput(
                request.getEmail(),
                request.getRawPassword())
        );

        response.addCookie(createCookie(output.getRefreshAuthorizationToken()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, output.getAuthorizationToken())
                .build();
    }

    private Cookie createCookie(String refreshAuthorizationToken) {
        Cookie cookie = new Cookie(
                REFRESH_AUTHORIZATION_COOKIE_KEY,
                refreshAuthorizationToken);

        cookie.setHttpOnly(true);

        return cookie;
    }
}
