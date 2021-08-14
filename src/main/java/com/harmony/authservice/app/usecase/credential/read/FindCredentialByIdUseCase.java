package com.harmony.authservice.app.usecase.credential.read;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByIdInput;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByIdOutput;
import org.springframework.stereotype.Service;

@Service
public class FindCredentialByIdUseCase implements UseCase<FindCredentialByIdInput, FindCredentialByIdOutput> {

    private final CredentialQueryGateway credentialQueryGateway;

    public FindCredentialByIdUseCase(CredentialQueryGateway credentialQueryGateway) {
        this.credentialQueryGateway = credentialQueryGateway;
    }

    @Override
    public FindCredentialByIdOutput execute(FindCredentialByIdInput input) throws Exception {

        Credential credential = credentialQueryGateway.findById(input.getCredentialId());

        return new FindCredentialByIdOutput(credential);
    }
}
