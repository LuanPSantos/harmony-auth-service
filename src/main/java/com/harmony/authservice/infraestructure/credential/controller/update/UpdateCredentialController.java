package com.harmony.authservice.infraestructure.credential.controller.update;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialOutput;
import com.harmony.authservice.domain.credential.model.*;
import com.harmony.authservice.infraestructure.credential.controller.update.io.UpdateCredentialRequest;
import com.harmony.authservice.infraestructure.credential.controller.update.io.UpdateCredentialResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("credentials")
public class UpdateCredentialController {

    private final UseCase<UpdateCredentialInput, UpdateCredentialOutput> updateCredentialUseCase;

    public UpdateCredentialController(
            @Qualifier("updateCredentialUseCase")
                    UseCase<UpdateCredentialInput, UpdateCredentialOutput> updateCredentialUseCase) {
        this.updateCredentialUseCase = updateCredentialUseCase;
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCredentialResponse update(@PathVariable Long id, @RequestBody @Valid UpdateCredentialRequest request) throws Exception {

        Credential credential = new Credential.Builder()
                .withId(id)
                .withEmail(request.getEmail())
                .withRawPassword(request.getRawPassword())
                .build();

        UpdateCredentialOutput output = updateCredentialUseCase.execute(new UpdateCredentialInput(credential, new Password(request.getOldRawPassword())));

        return new UpdateCredentialResponse(output.getCredential().getEmail().toString());
    }
}
