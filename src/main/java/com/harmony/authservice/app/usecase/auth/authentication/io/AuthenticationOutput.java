package com.harmony.authservice.app.usecase.auth.authentication.io;

import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.credential.model.CredentialId;

public class AuthenticationOutput {
    private final JWTAuthorizationTokenPair jwtAuthorizationTokenPair;
    private final CredentialId credentialId;

    public AuthenticationOutput(JWTAuthorizationTokenPair jwtAuthorizationTokenPair, CredentialId credentialId) {
        this.jwtAuthorizationTokenPair = jwtAuthorizationTokenPair;
        this.credentialId = credentialId;
    }

    public CredentialId getCredentialId() {
        return credentialId;
    }

    public JWTAuthorizationTokenPair getJwtAuthorizationTokenPair() {
        return jwtAuthorizationTokenPair;
    }
}
