package com.harmony.authservice.domain.auth.model;

import com.harmony.authservice.domain.credential.model.Role;
import com.harmony.authservice.domain.token.model.JWTTokens;

import java.util.AbstractMap.SimpleEntry;
import java.util.Objects;

public class JWTAuthorization {

    private static final String AUTHORIZATION_BEARER_PREFIX = "Bearer ";
    public static final String ROLE_FIELD = "role";

    private final String authorization;

    public JWTAuthorization(String email, Long expirationTime, Role role) {
        this.authorization = generateAuthorization(email, role, expirationTime);
    }

    private JWTAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public static JWTAuthorization fromAuthorizationToken(String authorizationToken) {
        String authorization = removePrefix(authorizationToken);

        JWTTokens.validateToken(authorization);

        return new JWTAuthorization(authorization);
    }

    public String getEmail() {
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

    private static String generateAuthorization(String email, Role role, Long timeToLive) {
        return JWTTokens.generateJwtToken(email, timeToLive, new SimpleEntry<>(ROLE_FIELD, role)).toString();
    }

    private static String removePrefix(String token) {
        return token.replace(AUTHORIZATION_BEARER_PREFIX, "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTAuthorization that = (JWTAuthorization) o;
        return Objects.equals(authorization, that.authorization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorization);
    }
}
