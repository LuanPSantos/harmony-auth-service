package com.harmony.authservice.app.usecase.passwordrecovery.createtoken;

import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.token.gateway.TokenCreatedEventGateway;
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
    private final TokenCreatedEventGateway tokenCreatedEventGateway;
    private final Long authorizationTokenTTL;

    public CreateTokenUseCase(
            @Value("${password-recovery-token.ttl}")
                    Long recoveryTokenTTL,
            CredentialQueryGateway credentialQueryGateway,
            TokenCreatedEventGateway tokenCreatedEventGateway
    ) {
        this.authorizationTokenTTL = recoveryTokenTTL;
        this.credentialQueryGateway = credentialQueryGateway;
        this.tokenCreatedEventGateway = tokenCreatedEventGateway;
    }

    @Override
    public Void execute(CreateTokenInput input) {

        credentialQueryGateway
                .findByEmail(input.getEmail())
                .ifPresent((credential -> {
                    Token token = JWTTokens.generateJwtToken(credential.getId().toString(), authorizationTokenTTL);

                    tokenCreatedEventGateway.send(token, credential.getEmail());
                }));

        return null;
    }
}
