package com.harmony.authservice.domain.credential.service.create;


import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Role;
import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final CredentialGateway credentialGateway;

    public CreateCredentialService(CredentialGateway credentialGateway) {
        this.credentialGateway = credentialGateway;
    }

    public Credential create(String email, String password, Role role) throws Exception {
        Credential credential = new Credential(email, passwordEncoder.encode(password), role);
        credential.validate();

        return credentialGateway.save(credential);
    }
}
