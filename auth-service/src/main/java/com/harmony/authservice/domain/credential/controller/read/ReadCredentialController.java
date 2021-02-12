package com.harmony.userregistration.credential.controller.read;

import com.harmony.userregistration.credential.controller.read.response.FindCredentialByEmailResponse;
import com.harmony.userregistration.credential.model.Credential;
import com.harmony.userregistration.credential.service.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/credentials")
public class ReadCredentialController {

    private final CredentialService credentialService;

    public ReadCredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FindCredentialByEmailResponse findByEmail(@RequestParam("email") String email) throws Exception {
        Credential credential = credentialService.findByEmail(email);

        return new FindCredentialByEmailResponse(credential.getEmail().getValue(), credential.getPassword().getValue());
    }
}
