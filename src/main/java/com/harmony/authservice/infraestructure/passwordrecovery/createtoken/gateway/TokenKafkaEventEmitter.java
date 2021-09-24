package com.harmony.authservice.infraestructure.passwordrecovery.createtoken.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.token.gateway.TokenCreatedEventEmitter;
import com.harmony.authservice.domain.token.model.Token;
import com.harmony.authservice.infraestructure.passwordrecovery.createtoken.gateway.io.TokenCreatedPayload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenKafkaEventEmitter implements TokenCreatedEventEmitter {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TokenKafkaEventEmitter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(Token token, Email email) {
        try {
            String json = new ObjectMapper().writeValueAsString(new TokenCreatedPayload(token.toString(), email.toString()));
            kafkaTemplate.send("password-recovery-token", "1", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
