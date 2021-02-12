package com.harmony.userregistration.credential.controller.create;

import com.harmony.userregistration.credential.controller.create.request.CreateCredentialRequest;
import com.harmony.userregistration.credential.model.Credential;
import com.harmony.userregistration.credential.service.create.CreateCredentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("users/credentials")
public class CreateCredentialController {

    private final CreateCredentialService createCredentialService;

    public CreateCredentialController(CreateCredentialService createCredentialService) {
        this.createCredentialService = createCredentialService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCredentialRequest request) throws Exception {
        Credential credential = createCredentialService.create(
                request.getEmail(),
                request.getPassword()
        );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(credential.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
