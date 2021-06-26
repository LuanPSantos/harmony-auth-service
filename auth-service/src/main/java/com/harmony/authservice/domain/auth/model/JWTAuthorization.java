package com.harmony.authservice.domain.auth.model;

import com.harmony.authservice.domain.credential.model.Role;

import java.util.AbstractMap.SimpleEntry;

public class JWTAuthorization {

    private static final String AUTHORIZATION_BEARER_PREFIX = "Bearer ";
    private static final String ROLE_FIELD = "role";

    private final String authorization;

    public JWTAuthorization(String authorization) {
        JWTTokens.checkTokenSignature(authorization);

        this.authorization = authorization;
    }

    public static JWTAuthorization withEmailAndExpirationTimeAndRole(String subject, Long expirationTime, Role role) {
        return new JWTAuthorization(generateAuthorization(subject, expirationTime, role));
    }

    public static JWTAuthorization validateAuthorizationToken(String authorizationToken) {
        String authorization = removingPrefix(authorizationToken);

        JWTTokens.checkTokenSignature(authorization);

        return new JWTAuthorization(authorization);
    }

    public String getSubject() {
        if (authorization != null) {
            return JWTTokens.extractSubjectFromJwtToken(authorization);
        }
        return null;
    }

    public Role getRole() {
        if (authorization != null) {
            return Role.valueOf(JWTTokens.extractCustomFieldFromJwtToken(authorization, ROLE_FIELD));
        }
        return null;
    }

    public String asToken() {
        return AUTHORIZATION_BEARER_PREFIX + authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    private static String generateAuthorization(String subject, Long timeToLive, Role role) {
        return JWTTokens.generateJwtToken(subject, timeToLive, new SimpleEntry<>(ROLE_FIELD, role));
    }

    private static String removingPrefix(String token) {
        return token.replace(AUTHORIZATION_BEARER_PREFIX, "");
    }

}
