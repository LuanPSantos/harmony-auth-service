package com.harmony.authservice.app.usecase.credential.create;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.create.CreateCredentialService;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
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

        return new CreateCredentialOutput(
                credential.getId(),
                credential.getEmail().getValue(),
                credential.getPassword().getValue(),
                credential.getRole()
        );
    }
}
