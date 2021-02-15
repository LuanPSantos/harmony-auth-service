package com.harmony.authservice.domain.auth.authorization.controller.request;

import com.harmony.authservice.domain.credential.model.Role;

public class AuthorizationRequest {

    private String authorizationToken;
    private String refreshAuthorizationToken;
    private Role roleRequiredByEndpoint;

    public AuthorizationRequest() {
    }

    public Role getRoleRequiredByEndpoint() {
        return roleRequiredByEndpoint;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public String getRefreshAuthorizationToken() {
        return refreshAuthorizationToken;
    }
}
