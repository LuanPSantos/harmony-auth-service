package com.harmony.userregistration.credential.service.create;

import com.harmony.userregistration.credential.model.Credential;
import com.harmony.userregistration.credential.service.CredentialService;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {

    private final CredentialService credentialService;

    public CreateCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public Credential create(String email, String password) throws Exception {
        Credential credential = new Credential(email, password);
        credential.validate();

        return credentialService.save(credential);
    }

}
