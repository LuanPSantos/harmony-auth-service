package com.harmony.authservice.app.usecase.auth.authorization.io;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;

public class AuthorizationOutput {
    private final JWTAuthorization jwtAuthorization;

    public AuthorizationOutput(JWTAuthorization jwtAuthorization) {
        this.jwtAuthorization = jwtAuthorization;
    }

    public JWTAuthorization getJwtAuthorization() {
        return jwtAuthorization;
    }
}
