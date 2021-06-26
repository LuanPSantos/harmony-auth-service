package com.harmony.authservice.app.usecase.credential.update;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.gateway.CreateCredentialGateway;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import org.springframework.stereotype.Service;

@Service
public class UpdateCredentialUseCase implements UseCase<UpdateCredentialInput, Void> {

    private final CredentialQueryGateway credentialQueryGateway;
    private final CreateCredentialGateway createCredentialGateway;

    public UpdateCredentialUseCase(
            CredentialQueryGateway credentialQueryGateway,
            CreateCredentialGateway createCredentialGateway) {
        this.credentialQueryGateway = credentialQueryGateway;
        this.createCredentialGateway = createCredentialGateway;
    }

    @Override
    public Void execute(UpdateCredentialInput input) throws Exception {

        Credential credential = credentialQueryGateway.findById(input.getCredential().getId());

        credential.updateEmail(input.getCredential().getEmail());
        credential.updatePassword(input.getCredential().getPassword());

        createCredentialGateway.create(credential);

        return null;
    }
}
