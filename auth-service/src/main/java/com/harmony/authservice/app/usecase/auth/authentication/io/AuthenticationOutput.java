package com.harmony.authservice.app.usecase.auth.authentication.io;

public class AuthenticationOutput {
    private final String authorizationToken;

    private final String refreshAuthorizationToken;

    public AuthenticationOutput(String authorizationToken, String refreshAuthorizationToken) {

        this.authorizationToken = authorizationToken;
        this.refreshAuthorizationToken = refreshAuthorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public String getRefreshAuthorizationToken() {
        return refreshAuthorizationToken;
    }
}