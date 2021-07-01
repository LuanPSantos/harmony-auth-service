package com.harmony.authservice.app.usecase.credential.update;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.gateway.UpdateCredentialGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateCredentialUseCase implements UseCase<UpdateCredentialInput, Void> {

    private final CredentialQueryGateway credentialQueryGateway;
    private final UpdateCredentialGateway updateCredentialGateway;

    public UpdateCredentialUseCase(
            CredentialQueryGateway credentialQueryGateway,
            UpdateCredentialGateway updateCredentialGateway) {
        this.credentialQueryGateway = credentialQueryGateway;
        this.updateCredentialGateway = updateCredentialGateway;
    }

    @Override
    public Void execute(UpdateCredentialInput input) throws Exception {

        Credential credential = credentialQueryGateway.findById(input.getCredential().getId());

        credential.updateEmail(input.getCredential().getEmail());
        credential.updatePassword(input.getCredential().getPassword());

        updateCredentialGateway.update(credential);

        return null;
    }
}