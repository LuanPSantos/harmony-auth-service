package com.harmony.authservice.app.usecase.passwordrecovery.createtoken;

import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.token.gateway.TokenCreatedEventEmitter;
import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.passwordrecovery.createtoken.io.CreateTokenInput;
import com.harmony.authservice.domain.token.model.JWTTokens;
import com.harmony.authservice.domain.token.model.Token;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateTokenUseCase implements UseCase<CreateTokenInput, Void> {

    private final CredentialQueryGateway credentialQueryGateway;
    private final TokenCreatedEventEmitter tokenCreatedEventEmitter;
    private final Long authorizationTokenTTL;

    public CreateTokenUseCase(
            @Value("${password-recovery-token.ttl}")
                    Long recoveryTokenTTL,
            CredentialQueryGateway credentialQueryGateway,
            TokenCreatedEventEmitter tokenCreatedEventEmitter) {
        this.authorizationTokenTTL = recoveryTokenTTL;
        this.credentialQueryGateway = credentialQueryGateway;
        this.tokenCreatedEventEmitter = tokenCreatedEventEmitter;
    }

    @Override
    public Void execute(CreateTokenInput input) throws Exception {

        try {

            Credential credential = credentialQueryGateway.findByEmail(input.getEmail());

            Token token = JWTTokens.generateJwtToken(authorizationTokenTTL);

            tokenCreatedEventEmitter.send(token, credential.getEmail());

        }catch (CredentialNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
