package com.harmony.authservice.app.usecase.auth.authorization.io;

public class AuthorizationOutput {
    private final String authorizationToken;

    public AuthorizationOutput(String authorizationToken) {

        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
