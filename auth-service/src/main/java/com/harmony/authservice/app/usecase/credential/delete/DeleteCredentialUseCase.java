package com.harmony.authservice.app.usecase.credential.delete;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.service.delete.DeleteCredentialService;
import com.harmony.authservice.app.usecase.credential.delete.io.DeleteCredentialInput;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialUseCase implements UseCase<DeleteCredentialInput, Void> {

    private final DeleteCredentialService deleteCredentialService;

    public DeleteCredentialUseCase(DeleteCredentialService deleteCredentialService) {
        this.deleteCredentialService = deleteCredentialService;
    }

    @Override
    public Void execute(DeleteCredentialInput payload) {

        deleteCredentialService.deleteById(payload.getId());

        return null;
    }
}
