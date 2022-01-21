package com.harmony.authservice.app.usecase.credential.create;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.gateway.CreateCredentialGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
import com.harmony.authservice.domain.credential.validator.ContainsInEmailValidation;
import com.harmony.authservice.domain.credential.validator.PasswordValidator;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.credential.model.EncodedPassword.encodeRawPassword;

@Service
public class CreateCredentialUseCase implements UseCase<CreateCredentialInput, CreateCredentialOutput> {

    private final CreateCredentialGateway createCredentialGateway;

    public CreateCredentialUseCase(CreateCredentialGateway createCredentialGateway) {
        this.createCredentialGateway = createCredentialGateway;
    }

    @Override
    public CreateCredentialOutput execute(CreateCredentialInput input) throws PasswordInvalidException {
        PasswordValidator validator = new PasswordValidator(input.getRawPassword());
        validator.addValidation(new ContainsInEmailValidation(input.getEmail()));

        validator.validate();

        Credential credential = createCredentialGateway
                .create(new Credential.Builder()
                        .withEmail(input.getEmail())
                        .withEncodedPassword(encodeRawPassword(input.getRawPassword()))
                        .withRole(input.getRole())
                        .build());

        return new CreateCredentialOutput(credential.getId());
    }
}
