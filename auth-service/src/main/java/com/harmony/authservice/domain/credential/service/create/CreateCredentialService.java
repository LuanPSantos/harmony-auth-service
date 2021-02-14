package com.harmony.authservice.domain.credential.service.create;


import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final CredentialService credentialService;

    public CreateCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public Credential create(String email, String password) throws Exception {
        Credential credential = new Credential(email, passwordEncoder.encode(password));
        credential.validate();

        return credentialService.save(credential);
    }

}
