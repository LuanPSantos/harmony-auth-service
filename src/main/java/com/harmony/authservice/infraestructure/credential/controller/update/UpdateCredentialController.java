package com.harmony.authservice.infraestructure.credential.controller.update;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.update.io.UpdateCredentialInput;
import com.harmony.authservice.domain.credential.model.*;
import com.harmony.authservice.infraestructure.credential.controller.update.io.UpdateCredentialRequest;
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

        Credential credential = new Credential.Builder()
                .withId(id)
                .withEmail(request.getEmail())
                .withRawPassword(request.getRawPassword())
                .build();

        updateCredentialUseCase.execute(new UpdateCredentialInput(credential));
    }
}
