package com.harmony.authservice.domain.auth.authentication.controller;

import com.harmony.authservice.domain.auth.authentication.controller.request.AuthenticationRequest;
import com.harmony.authservice.domain.auth.authentication.service.AuthenticationService;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair.REFRESH_AUTHENTICATION_COOKIE_KEY;

@RestController
@RequestMapping("authentications")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Void> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) throws Exception {
        JWTAuthorizationTokenPair authenticatedTokenPair = authenticationService
                .authenticate(request.getUsername(), request.getPassword());

        Cookie cookie = new Cookie(REFRESH_AUTHENTICATION_COOKIE_KEY, authenticatedTokenPair.getRefreshAuthorization().getAuthorization());

        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        System.out.println("Autenticado!");

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, authenticatedTokenPair.getAuthorization().getAuthorizationToken())
                .build();
    }
}
