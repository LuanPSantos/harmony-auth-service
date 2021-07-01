package com.harmony.authservice.infraestructure.credential.controller.update;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.CredentialId;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.infraestructure.credential.controller.update.request.UpdateCredentialRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credentials")
public class UpdateCredentialController {

    @Qualifier("updateCredentialUseCase")
    private final UseCase<UpdateCredentialInput, Void> updateCredentialUseCase;

    public UpdateCredentialController(UseCase<UpdateCredentialInput, Void> updateCredentialUseCase) {
        this.updateCredentialUseCase = updateCredentialUseCase;
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody UpdateCredentialRequest request) throws Exception {
        updateCredentialUseCase.execute(new UpdateCredentialInput(
                new Credential(
                        new CredentialId(id),
                        new Email(request.getEmail()),
                        new Password(request.getPassword())
                )));
    }
}
