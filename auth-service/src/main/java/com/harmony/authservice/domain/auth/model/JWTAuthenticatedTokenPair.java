package com.harmony.authservice.domain.auth.model;

public class JWTAuthenticatedTokenPair {

    private final JWTAuthorization authorization;
    private final JWTAuthorization refreshAuthorization;

    public JWTAuthenticatedTokenPair(JWTAuthorization authorization, JWTAuthorization refreshAuthorization) {
        this.authorization = authorization;
        this.refreshAuthorization = refreshAuthorization;
    }

    public JWTAuthorization getAuthorization() {
        return authorization;
    }

    public JWTAuthorization getRefreshAuthorization() {
        return refreshAuthorization;
    }
}
