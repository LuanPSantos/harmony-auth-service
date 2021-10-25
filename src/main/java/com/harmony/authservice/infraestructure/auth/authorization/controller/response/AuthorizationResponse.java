package com.harmony.authservice.infraestructure.auth.authorization.controller.response;

public class AuthorizationResponse {

    private String authorizationToken;

    public AuthorizationResponse() {
    }

    public AuthorizationResponse(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
