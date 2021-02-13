package com.harmony.authservice.domain.auth.model;

import com.harmony.authservice.common.jwt.JWTUtils;

import java.util.Date;

public class JWTAuthorization {

    private static final String AUTHORIZATION_BEARER_PREFIX = "Bearer ";

    private final String authorization;

    private JWTAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public static JWTAuthorization withSubject(String subject) {
        return new JWTAuthorization(generateAuthorization(subject));
    }

    public static JWTAuthorization withAuthorizationToken(String authorizationToken) {
        return new JWTAuthorization(removingPrefix(authorizationToken));
    }

    public boolean isValid() {
        return JWTUtils.isValid(authorization) && isNotExpired();
    }

    public String getSubject() {
        if (authorization != null) {
            return JWTUtils.extractSubjectFromJwtToken(authorization);
        }
        return null;
    }

    private boolean isNotExpired() {
        Date expiration = extractExpirationFromAuthorization();

        return expiration.before(new Date());
    }

    public String getToken() {
        return AUTHORIZATION_BEARER_PREFIX + authorization;
    }

    private static String generateAuthorization(String subject) {
        return JWTUtils.generateJwtToken(subject);
    }

    private Date extractExpirationFromAuthorization() {
        if (authorization != null) {
            return JWTUtils.extractExpirationFromJwtToken(authorization);
        }

        throw new IllegalStateException("Token de authorization null");
    }

    private static String removingPrefix(String token) {
        return token.replace(AUTHORIZATION_BEARER_PREFIX, "");
    }

}
