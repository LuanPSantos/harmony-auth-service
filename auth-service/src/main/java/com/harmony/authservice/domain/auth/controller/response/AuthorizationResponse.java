package com.harmony.authservice.domain.auth.controller.response;

public class AuthorizationResponse {

    private String authorization;

    public AuthorizationResponse(String authorization) {
        this.authorization = authorization;
    }

    public AuthorizationResponse() {
    }

    public String getAuthorization() {
        return authorization;
    }
}
