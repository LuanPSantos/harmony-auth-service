package com.harmony.authservice.infraestructure.passwordrecovery.createpassword.controller;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.app.usecase.passwordrecovery.createpassword.io.CreatePasswordInput;
import com.harmony.authservice.domain.credential.model.RawPassword;
import com.harmony.authservice.domain.token.model.Token;
import com.harmony.authservice.infraestructure.passwordrecovery.createpassword.controller.io.CreatePasswordRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePasswordController {

    private final UseCase<CreatePasswordInput, Void> createPasswordUseCase;

    public CreatePasswordController(UseCase<CreatePasswordInput, Void> createPasswordUseCase) {
        this.createPasswordUseCase = createPasswordUseCase;
    }

    @PostMapping("passwords")
    public void create(@RequestHeader("X-Password-Recovery-Token") String token, @RequestBody CreatePasswordRequest request) throws Exception {
        createPasswordUseCase.execute(new CreatePasswordInput(new Token(token), new RawPassword(request.getPassword())));
    }
}
