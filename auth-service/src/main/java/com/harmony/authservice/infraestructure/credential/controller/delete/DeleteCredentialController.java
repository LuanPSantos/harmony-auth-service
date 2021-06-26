package com.harmony.authservice.infraestructure.credential.controller.delete;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.delete.io.DeleteCredentialInput;
import com.harmony.authservice.domain.credential.model.CredentialId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credentials")
public class DeleteCredentialController {

    @Qualifier("deleteCredentialUseCase")
    private final UseCase<DeleteCredentialInput, Void> deleteCredentialUseCase;

    public DeleteCredentialController(UseCase<DeleteCredentialInput, Void> deleteCredentialUseCase) {
        this.deleteCredentialUseCase = deleteCredentialUseCase;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) throws Exception {
        deleteCredentialUseCase.execute(new DeleteCredentialInput(new CredentialId(id)));
    }
}
