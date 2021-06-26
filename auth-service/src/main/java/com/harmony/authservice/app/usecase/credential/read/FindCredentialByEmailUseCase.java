package com.harmony.authservice.app.usecase.credential.read;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailInput;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailOutput;
import org.springframework.stereotype.Service;

@Service
public class FindCredentialByEmailUseCase implements UseCase<FindCredentialByEmailInput, FindCredentialByEmailOutput> {

    private final CredentialQueryGateway credentialQueryGateway;

    public FindCredentialByEmailUseCase(CredentialQueryGateway credentialQueryGateway) {
        this.credentialQueryGateway = credentialQueryGateway;
    }

    @Override
    public FindCredentialByEmailOutput execute(FindCredentialByEmailInput input) throws Exception {

        Credential credential = credentialQueryGateway.findByEmail(input.getEmail());

        return new FindCredentialByEmailOutput(credential);
    }
}
