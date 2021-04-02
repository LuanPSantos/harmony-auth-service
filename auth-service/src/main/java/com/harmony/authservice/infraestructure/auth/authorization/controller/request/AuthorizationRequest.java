package com.harmony.authservice.infraestructure.auth.authorization.controller.request;

import com.harmony.authservice.domain.credential.model.Role;

public class AuthorizationRequest {

    private Role roleRequiredByEndpoint;

    public AuthorizationRequest() {
    }

    public Role getRoleRequiredByEndpoint() {
        return roleRequiredByEndpoint;
    }
}
