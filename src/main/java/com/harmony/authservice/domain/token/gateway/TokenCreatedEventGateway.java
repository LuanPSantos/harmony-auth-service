package com.harmony.authservice.domain.token.gateway;

import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.token.model.Token;

public interface TokenCreatedEventGateway {

    void send(Token token, Email email);
}
