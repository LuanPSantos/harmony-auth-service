package com.harmony.authservice.infraestructure.credential.controller.create;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.model.RawPassword;
import com.harmony.authservice.infraestructure.credential.controller.create.request.CreateCredentialRequest;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
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

    @Qualifier("createCredentialUseCase")
    private final UseCase<CreateCredentialInput, CreateCredentialOutput> createCredentialUseCase;

    public CreateCredentialController(UseCase<CreateCredentialInput, CreateCredentialOutput> createCredentialUseCase) {
        this.createCredentialUseCase = createCredentialUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateCredentialOutput> create(@RequestBody @Valid CreateCredentialRequest request) throws Exception {
        CreateCredentialOutput output = createCredentialUseCase.execute(new CreateCredentialInput(
                new Credential(
                        new Email(request.getEmail()),
                        new RawPassword(request.getPassword()),
                        request.getRole())
        ));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.getCredentialId().get())
                .toUri();

        return ResponseEntity.created(uri).body(output);
    }
}
