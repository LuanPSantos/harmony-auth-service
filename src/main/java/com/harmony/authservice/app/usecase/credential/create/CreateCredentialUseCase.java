package com.harmony.authservice.app.usecase.credential.create;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.gateway.CreateCredentialGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialUseCase implements UseCase<CreateCredentialInput, CreateCredentialOutput> {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final CreateCredentialGateway createCredentialGateway;

    public CreateCredentialUseCase(CreateCredentialGateway createCredentialGateway) {
        this.createCredentialGateway = createCredentialGateway;
    }

    @Override
    public CreateCredentialOutput execute(CreateCredentialInput input) throws PasswordInvalidException {
        input.getCredential().validate();

        String encodedPassword = passwordEncoder.encode(input.getCredential().getPassword().get());

        Credential credential = new Credential(
                input.getCredential().getEmail(),
                new Password(encodedPassword),
                input.getCredential().getRole()
        );

        credential = createCredentialGateway.create(credential);

        return new CreateCredentialOutput(credential.getId());
    }
}