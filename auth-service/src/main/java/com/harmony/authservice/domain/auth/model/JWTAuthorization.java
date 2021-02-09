package com.harmony.authservice.domain.auth.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harmony.authservice.common.jwt.JWTUtils;

import java.util.Date;

public class JWTAuthorization {

    private static final String AUTHORIZATION_BEARER_PREFIX = "Bearer";

    private final ObjectMapper mapper = new ObjectMapper();

    private final String value;

    public JWTAuthorization(Subject subject) throws JsonProcessingException {

        this.value = generateAuthorization(mapper.writeValueAsString(subject));
    }

    public JWTAuthorization(String jwtAuthorization) {
        value = jwtAuthorization;
    }

    public Subject getSubject() throws JsonProcessingException {
        if (value != null) {
            String subjectInJson = JWTUtils.extractSubjectFromJwtToken(value.replace(AUTHORIZATION_BEARER_PREFIX, ""));
            return mapper.readValue(subjectInJson, Subject.class);
        }
        return null;
    }

    public boolean isExpired() {
        Date expiration = extractExpirationFromAuthorization(value);

        return expiration.before(new Date());
    }

    public String getToken() {
        return value;
    }

    private String generateAuthorization(String subject) {
        String jwt = JWTUtils.generateJwtToken(subject);

        return AUTHORIZATION_BEARER_PREFIX + " " + jwt;
    }

    private Date extractExpirationFromAuthorization(String authorization) {
        if (authorization != null) {
            return JWTUtils.extractExpirationFromJwtToken(authorization.replace(AUTHORIZATION_BEARER_PREFIX, ""));
        }

        throw new IllegalStateException("Token de authorization null");
    }
}
