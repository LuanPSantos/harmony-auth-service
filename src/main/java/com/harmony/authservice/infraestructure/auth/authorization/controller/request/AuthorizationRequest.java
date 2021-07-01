package com.harmony.authservice.infraestructure.auth.authorization.controller.request;

import com.harmony.authservice.domain.credential.model.Role;

import javax.validation.constraints.NotNull;

public class AuthorizationRequest {

    @NotNull(message = "O campo 'roleRequiredByEndpoint' não pode ser nulo")
    private Role roleRequiredByEndpoint;

    public AuthorizationRequest() {
    }

    public Role getRoleRequiredByEndpoint() {
        return roleRequiredByEndpoint;
    }
}
