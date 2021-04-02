package com.harmony.authservice.domain.credential.usecase.update;

import com.harmony.authservice.domain.usecase.UseCase;
import com.harmony.authservice.domain.credential.service.update.UpdateCredentialService;
import com.harmony.authservice.domain.credential.usecase.update.io.UpdateCredentialInput;
import org.springframework.stereotype.Service;

@Service
public class UpdateCredentialUseCase implements UseCase<UpdateCredentialInput, Void> {

    private final UpdateCredentialService updateCredentialService;

    public UpdateCredentialUseCase(UpdateCredentialService updateCredentialService) {
        this.updateCredentialService = updateCredentialService;
    }

    @Override
    public Void execute(UpdateCredentialInput payload) throws Exception {

        updateCredentialService.update(
                payload.getId(),
                payload.getEmail(),
                payload.getPassword());

        return null;
    }
}
