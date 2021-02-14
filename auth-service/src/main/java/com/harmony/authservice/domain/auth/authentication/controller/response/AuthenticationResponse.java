package com.harmony.authservice.domain.auth.authentication.controller.response;

public class AuthenticationResponse {

    private String authorizationToken;
    private String refreshAuthorizationToken;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String authorizationToken, String refreshAuthorizationToken) {
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
