package com.harmony.authservice.infraestructure.auth.authentication.controller.response;

public class AuthenticationResponse {

    private Long credentialId;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(Long credentialId) {
        this.credentialId = credentialId;
    }

    public Long getCredentialId() {
        return credentialId;
    }
}
