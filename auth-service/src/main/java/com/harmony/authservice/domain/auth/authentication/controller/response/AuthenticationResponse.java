package com.harmony.authservice.domain.auth.authentication.controller.response;

public class AuthenticationResponse {

    private String authorizationToken;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
