package com.harmony.authservice.domain.auth.authorization.usecase.io;

public class AuthorizationOutput {
    private final String authorizationToken;

    public AuthorizationOutput(String authorizationToken) {

        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
