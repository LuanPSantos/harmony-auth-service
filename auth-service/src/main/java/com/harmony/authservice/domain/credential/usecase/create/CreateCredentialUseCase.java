package com.harmony.authservice.domain.credential.usecase.create;

import com.harmony.authservice.domain.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.create.CreateCredentialService;
import com.harmony.authservice.domain.credential.usecase.create.io.CreateCredentialInput;
import com.harmony.authservice.domain.credential.usecase.create.io.CreateCredentialOutput;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialUseCase implements UseCase<CreateCredentialInput, CreateCredentialOutput> {

    private final CreateCredentialService createCredentialService;

    public CreateCredentialUseCase(CreateCredentialService createCredentialService) {
        this.createCredentialService = createCredentialService;
    }

    @Override
    public CreateCredentialOutput execute(CreateCredentialInput input) throws Exception {

        Credential credential = createCredentialService.create(
                input.getEmail(),
                input.getPassword(),
                input.getRole());

        // Notify creation

        return new CreateCredentialOutput(
                credential.getId(),
                credential.getEmail().getValue(),
                credential.getPassword().getValue(),
                credential.getRole()
        );
    }
}
