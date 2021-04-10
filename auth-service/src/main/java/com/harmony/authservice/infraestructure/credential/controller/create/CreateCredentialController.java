package com.harmony.authservice.infraestructure.credential.controller.create;

import com.harmony.authservice.app.usecase.UseCase;
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
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCredentialRequest request) throws Exception {
        CreateCredentialOutput output = createCredentialUseCase.execute(new CreateCredentialInput(
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        ));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
