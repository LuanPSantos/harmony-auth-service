package com.harmony.authservice.domain.credential.controller.read;

import com.harmony.authservice.domain.credential.controller.read.response.FindCredentialByEmailResponse;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auths/credentials")
public class ReadCredentialController {

    private final CredentialService credentialService;

    public ReadCredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FindCredentialByEmailResponse findByEmail(@RequestParam("email") String email) throws Exception {
        Credential credential = credentialService.findByEmail(email);

        return new FindCredentialByEmailResponse(credential.getUsername(), credential.getPassword());
    }
}
