package com.harmony.authservice.domain.auth.model;

import com.harmony.authservice.common.jwt.JWTToken;

public class JWTAuthorization {

    public static final Long REFRESH_TOKEN_EXPIRATION_TIME = 120_000L;
    private static final String AUTHORIZATION_BEARER_PREFIX = "Bearer ";

    private final String authorization;

    private JWTAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public static JWTAuthorization withSubject(String subject) {
        return new JWTAuthorization(generateAuthorization(subject));
    }

    public static JWTAuthorization withSubjectAndExpirationTime(String subject, Long expirationTime) {
        return new JWTAuthorization(generateAuthorization(subject, expirationTime));
    }

    public static JWTAuthorization validateAndCreateFromToken(String authorizationToken) {
        String token = removingPrefix(authorizationToken);

        JWTToken.checkTokenSignature(token);

        return new JWTAuthorization(token);
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

    private static String generateAuthorization(String subject, Long expirationTime) {
        return JWTToken.generateJwtToken(subject, expirationTime);
    }

    private static String removingPrefix(String token) {
        return token.replace(AUTHORIZATION_BEARER_PREFIX, "");
    }

}
