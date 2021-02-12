package com.harmony.authservice.domain.credential.service.create;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {

    private final CredentialService credentialService;
    private final PasswordEncoder passwordEncoder;

    public CreateCredentialService(CredentialService credentialService, PasswordEncoder passwordEncoder) {
        this.credentialService = credentialService;
        this.passwordEncoder = passwordEncoder;
    }

    public Credential create(String email, String password) throws Exception {
        Credential credential = new Credential(email, passwordEncoder.encode(password));
        credential.validate();

        return credentialService.save(credential);
    }

}
