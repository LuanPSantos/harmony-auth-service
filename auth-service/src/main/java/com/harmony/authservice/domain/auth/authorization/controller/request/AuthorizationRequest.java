package com.harmony.authservice.domain.auth.authorization.controller.request;

public class AuthorizationRequest {

    private String authorizationToken;
    private String refreshAuthorizationToken;

    public AuthorizationRequest() {
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public String getRefreshAuthorizationToken() {
        return refreshAuthorizationToken;
    }
}
