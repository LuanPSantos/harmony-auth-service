package com.harmony.authservice.app.usecase.credential.delete;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.gateway.DeleteCredentialGateway;
import com.harmony.authservice.app.usecase.credential.delete.io.DeleteCredentialInput;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialUseCase implements UseCase<DeleteCredentialInput, Void> {

    private final DeleteCredentialGateway deleteCredentialGateway;

    public DeleteCredentialUseCase(DeleteCredentialGateway deleteCredentialGateway) {
        this.deleteCredentialGateway = deleteCredentialGateway;
    }

    @Override
    public Void execute(DeleteCredentialInput input) {

        deleteCredentialGateway.deleteById(input.getCredentialId());

        return null;
    }
}
