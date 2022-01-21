package com.harmony.authservice.app.usecase.credential.update;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialOutput;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.exception.PasswordDidNotMatchException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.gateway.SaveCredentialGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.domain.credential.model.EncodedPassword;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.credential.model.EncodedPassword.encodeRawPassword;

@Service
public class UpdateCredentialUseCase implements UseCase<UpdateCredentialInput, UpdateCredentialOutput> {

    private final CredentialQueryGateway credentialQueryGateway;
    private final SaveCredentialGateway saveCredentialGateway;

    public UpdateCredentialUseCase(
            CredentialQueryGateway credentialQueryGateway,
            SaveCredentialGateway saveCredentialGateway) {
        this.credentialQueryGateway = credentialQueryGateway;
        this.saveCredentialGateway = saveCredentialGateway;
    }

    @Override
    public UpdateCredentialOutput execute(UpdateCredentialInput input) throws PasswordDidNotMatchException, CredentialNotFoundException {

        Credential credential = credentialQueryGateway.findById(input.getId());

        if (credential.getPassword().matches(input.getOldRawPassword())) {
            credential.updateEmail(input.getEmail());

            if(input.getNewRawPassword() != null && input.getNewRawPassword().get() != null){
                credential.updatePassword(encodeRawPassword(input.getNewRawPassword()));
            }

            return new UpdateCredentialOutput(
                    saveCredentialGateway.save(credential)
            );
        } else {
            throw new PasswordDidNotMatchException();
        }
    }
}
