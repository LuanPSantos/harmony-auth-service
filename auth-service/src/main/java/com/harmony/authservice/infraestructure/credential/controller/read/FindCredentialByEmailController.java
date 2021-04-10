package com.harmony.authservice.infraestructure.credential.controller.read;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailInput;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailOutput;
import com.harmony.authservice.infraestructure.credential.controller.read.response.FindCredentialByEmailResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credentials")
public class FindCredentialByEmailController {

    @Qualifier("findCredentialByEmailUseCase")
    private final UseCase<FindCredentialByEmailInput, FindCredentialByEmailOutput> findCredentialByEmailUseCase;

    public FindCredentialByEmailController(UseCase<FindCredentialByEmailInput, FindCredentialByEmailOutput> findCredentialByEmailUseCase) {
        this.findCredentialByEmailUseCase = findCredentialByEmailUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FindCredentialByEmailResponse findByEmail(@RequestParam("email") String email) throws Exception {
        FindCredentialByEmailOutput output = findCredentialByEmailUseCase.execute(new FindCredentialByEmailInput(email));

        return new FindCredentialByEmailResponse(output.getEmail().getValue(), output.getPassword().getValue());
    }
}
