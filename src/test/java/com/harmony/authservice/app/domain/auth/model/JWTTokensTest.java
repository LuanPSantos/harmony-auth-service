package com.harmony.authservice.app.domain.auth.model;

import com.harmony.authservice.domain.auth.model.JWTTokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleEntry;

public class JWTTokensTest {

    private final String SUBJECT = "SUBJECT";
    private final Long TTL = 1000L;
    private final String KEY = "KEY";
    private final String VALUE = "VALUE";
    private final SimpleEntry<String, String> CUSTOM_FIELDS = new SimpleEntry<>(KEY, VALUE);


    @Test
    void ShouldGenerateAJWTToken() {
        String jwt = JWTTokens.generateJwtToken(SUBJECT, TTL,CUSTOM_FIELDS);

        Assertions.assertNotNull(jwt);
    }

    @Test
    void ShouldGetSubjectAndCustomFieldFromJWT() {
        String jwt = JWTTokens.generateJwtToken(SUBJECT, TTL,CUSTOM_FIELDS);

        String subject = JWTTokens.extractSubjectFromJwtToken(jwt);
        String customValue = JWTTokens.extractCustomFieldFromJwtToken(jwt, KEY);

        Assertions.assertEquals(SUBJECT, subject);
        Assertions.assertEquals(VALUE, customValue);
    }

    @Test
    void ShouldValidateJWTSignature() {
        String jwt = JWTTokens.generateJwtToken(SUBJECT, TTL,CUSTOM_FIELDS);

        JWTTokens.checkTokenSignature(jwt);

        Assertions.assertTrue(true);
    }
}
