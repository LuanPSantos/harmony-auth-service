package com.harmony.authservice.infraestructure.passwordrecovery.createtoken.controller;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.passwordrecovery.createtoken.io.CreateTokenInput;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.infraestructure.passwordrecovery.createtoken.controller.io.CreateTokenRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTokenController {

    @Qualifier("createTokenUseCase")
    private final UseCase<CreateTokenInput, Void> createTokenUseCase;

    public CreateTokenController(UseCase<CreateTokenInput, Void> createTokenUseCase) {
        this.createTokenUseCase = createTokenUseCase;
    }

    @PostMapping("passwords/recoveries")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendPasswordRecoveryToken(@RequestBody CreateTokenRequest request) throws Exception {
        createTokenUseCase.execute(new CreateTokenInput(new Email(request.getEmail())));
    }
}
