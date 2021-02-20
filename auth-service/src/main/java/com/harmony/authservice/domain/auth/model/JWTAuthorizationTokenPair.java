package com.harmony.authservice.domain.auth.model;

public class JWTAuthorizationTokenPair {

    public static final String REFRESH_AUTHENTICATION_COOKIE_KEY = "refresh-authentication";

    private final JWTAuthorization authorization;
    private final JWTAuthorization refreshAuthorization;

    public JWTAuthorizationTokenPair(JWTAuthorization authorization, JWTAuthorization refreshAuthorization) {
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
