package com.harmony.authservice.domain.auth.model;

import com.harmony.authservice.common.jwt.JWTToken;

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
        String token = removingPrefix(authorizationToken);

        JWTToken.checkTokenSignature(token);

        return new JWTAuthorization(token);
    }

    public boolean isExpired() {
        return JWTToken.isExpired(authorization);
    }

    public String getSubject() {
        if (authorization != null) {
            return JWTToken.extractSubjectFromJwtToken(authorization);
        }
        return null;
    }

    public String getToken() {
        return AUTHORIZATION_BEARER_PREFIX + authorization;
    }

    private static String generateAuthorization(String subject) {
        return JWTToken.generateJwtToken(subject);
    }

    private static String removingPrefix(String token) {
        return token.replace(AUTHORIZATION_BEARER_PREFIX, "");
    }

}
