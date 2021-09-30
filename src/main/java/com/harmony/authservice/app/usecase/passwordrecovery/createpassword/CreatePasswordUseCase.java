package com.harmony.authservice.app.usecase.passwordrecovery.createpassword;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.passwordrecovery.createpassword.io.CreatePasswordInput;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.gateway.SaveCredentialGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.CredentialId;
import com.harmony.authservice.domain.token.model.JWTTokens;
import com.harmony.authservice.domain.token.model.Token;
import org.springframework.stereotype.Component;

@Component
public class CreatePasswordUseCase implements UseCase<CreatePasswordInput, Void> {

    private final CredentialQueryGateway credentialQueryGateway;
    private final SaveCredentialGateway saveCredentialGateway;

    public CreatePasswordUseCase(CredentialQueryGateway credentialQueryGateway, SaveCredentialGateway saveCredentialGateway) {
        this.credentialQueryGateway = credentialQueryGateway;
        this.saveCredentialGateway = saveCredentialGateway;
    }

    @Override
    public Void execute(CreatePasswordInput input) throws Exception {

        Token token = input.getPasswordRecoveryToken();

        CredentialId credentialId = getCredentialIdFromToken(token);

        Credential credential = credentialQueryGateway.findById(credentialId);

        credential.updatePassword(input.getPassword());

        saveCredentialGateway.save(credential);

        return null;
    }

    private CredentialId getCredentialIdFromToken(Token token) {

        String subject = JWTTokens.extractSubjectFromJwtToken(token.toString());

        Long credentialId = Long.valueOf(subject);

        return new CredentialId(credentialId);
    }
}
