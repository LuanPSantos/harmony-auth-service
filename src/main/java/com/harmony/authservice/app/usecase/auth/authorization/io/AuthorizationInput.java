package com.harmony.authservice.app.usecase.auth.authorization.io;

import com.harmony.authservice.domain.credential.model.Role;

public class AuthorizationInput {
    private final String authorizationToken;
    private final String refreshAuthorizationToken;
    private final Role requiredRole;

    public AuthorizationInput(String authorizationToken, String refreshAuthentication, Role requiredRole) {
        this.authorizationToken = authorizationToken;
        this.refreshAuthorizationToken = refreshAuthentication;
        this.requiredRole = requiredRole;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public String getRefreshAuthorizationToken() {
        return refreshAuthorizationToken;
    }

    public Role getRequiredRole() {
        return requiredRole;
    }
}
