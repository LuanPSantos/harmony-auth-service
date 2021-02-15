package com.harmony.authservice.domain.auth.model;

import com.harmony.authservice.common.jwt.JWTTokens;
import com.harmony.authservice.domain.credential.model.Role;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class JWTAuthorization {

    public static final Long REFRESH_TOKEN_EXPIRATION_TIME = 120_000L;
    private static final String AUTHORIZATION_BEARER_PREFIX = "Bearer ";
    private static final String ROLE_FIELD = "role";

    private final String authorization;

    private JWTAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public static JWTAuthorization withEmailAndRole(String subject, Role role) {
        return new JWTAuthorization(generateAuthorization(subject, role));
    }

    public static JWTAuthorization withEmailAndExpirationTimeAndRole(String subject, Long expirationTime, Role role) {
        return new JWTAuthorization(generateAuthorization(subject, expirationTime, role));
    }

    public static JWTAuthorization validateAuthorizationToken(String authorizationToken) {
        String token = removingPrefix(authorizationToken);

        JWTTokens.checkTokenSignature(token);

        return new JWTAuthorization(token);
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

    public String getToken() {
        return AUTHORIZATION_BEARER_PREFIX + authorization;
    }

    private static String generateAuthorization(String subject, Role role) {
        return JWTTokens.generateJwtToken(subject, new SimpleEntry<>(ROLE_FIELD, role));
    }

    private static String generateAuthorization(String subject, Long expirationTime, Role role) {
        return JWTTokens.generateJwtToken(subject, expirationTime, new SimpleEntry<>(ROLE_FIELD, role));
    }

    private static String removingPrefix(String token) {
        return token.replace(AUTHORIZATION_BEARER_PREFIX, "");
    }

}
