package com.harmony.authservice.app.usecase.auth.authentication.io;

import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;

public class AuthenticationOutput {
    private final JWTAuthorizationTokenPair jwtAuthorizationTokenPair;

    public AuthenticationOutput(JWTAuthorizationTokenPair jwtAuthorizationTokenPair) {
        this.jwtAuthorizationTokenPair = jwtAuthorizationTokenPair;
    }

    public JWTAuthorizationTokenPair getJwtAuthorizationTokenPair() {
        return jwtAuthorizationTokenPair;
    }
}
