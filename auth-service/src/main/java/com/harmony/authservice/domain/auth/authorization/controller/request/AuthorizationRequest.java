package com.harmony.authservice.domain.auth.authorization.controller.request;

public class AuthorizationRequest {

    private String authorizationToken;

    public AuthorizationRequest() {
    }

    public AuthorizationRequest(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
