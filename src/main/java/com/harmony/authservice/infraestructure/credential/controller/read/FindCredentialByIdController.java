package com.harmony.authservice.infraestructure.credential.controller.read;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByIdInput;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByIdOutput;
import com.harmony.authservice.domain.credential.model.CredentialId;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.infraestructure.credential.controller.read.io.FindCredentialByEmailResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credentials")
public class FindCredentialByIdController {

    private final UseCase<FindCredentialByIdInput, FindCredentialByIdOutput> findCredentialByIdUseCase;

    public FindCredentialByIdController(
            @Qualifier("findCredentialByIdUseCase")
            UseCase<FindCredentialByIdInput, FindCredentialByIdOutput> findCredentialByIdUseCase) {
        this.findCredentialByIdUseCase = findCredentialByIdUseCase;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public FindCredentialByEmailResponse findById(@PathVariable Long id) throws Exception {
        FindCredentialByIdOutput output = findCredentialByIdUseCase.execute(new FindCredentialByIdInput(new CredentialId(id)));

        return new FindCredentialByEmailResponse(
                output.getCredential().getId().get(),
                output.getCredential().getEmail().get());
    }
}
