package com.harmony.authservice.infraestructure.credential.controller.create;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.RawPassword;
import com.harmony.authservice.infraestructure.credential.controller.create.io.CreateCredentialRequest;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
import com.harmony.authservice.infraestructure.credential.controller.create.io.CreateCredentialResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("credentials")
public class CreateCredentialController {

    private final UseCase<CreateCredentialInput, CreateCredentialOutput> createCredentialUseCase;

    public CreateCredentialController(
            @Qualifier("createCredentialUseCase")
                    UseCase<CreateCredentialInput, CreateCredentialOutput> createCredentialUseCase) {
        this.createCredentialUseCase = createCredentialUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateCredentialResponse> create(@RequestBody @Valid CreateCredentialRequest request) throws Exception {

        CreateCredentialOutput output = createCredentialUseCase.execute(
                new CreateCredentialInput(
                        new Email(request.getEmail()),
                        new RawPassword(request.getRawPassword()),
                        request.getRole()
                ));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.getCredentialId().toLong())
                .toUri();

        return ResponseEntity.created(uri).body(new CreateCredentialResponse(output.getCredentialId().toLong()));
    }
}
