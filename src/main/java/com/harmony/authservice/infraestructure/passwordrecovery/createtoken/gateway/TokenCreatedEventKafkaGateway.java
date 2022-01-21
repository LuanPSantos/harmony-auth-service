package com.harmony.authservice.infraestructure.passwordrecovery.createtoken.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.token.gateway.TokenCreatedEventGateway;
import com.harmony.authservice.domain.token.model.Token;
import com.harmony.authservice.infraestructure.passwordrecovery.createtoken.gateway.io.TokenCreatedPayload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenCreatedEventKafkaGateway implements TokenCreatedEventGateway {

    private final static String TOPIC = "password-recovery-token";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TokenCreatedEventKafkaGateway(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(Token token, Email email) {
        try {
            String jsonMessage = new ObjectMapper()
                    .writeValueAsString(new TokenCreatedPayload(token.toString(), email.toString()));

            kafkaTemplate.send(TOPIC, jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
