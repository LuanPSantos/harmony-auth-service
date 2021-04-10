package com.harmony.authservice.domain.credential.service.update;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.app.util.ObjectUtils.notNullOrDefault;

@Service
public class UpdateCredentialService {

    private final CredentialGateway credentialGateway;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UpdateCredentialService(CredentialGateway credentialGateway) {
        this.credentialGateway = credentialGateway;
    }

    public void update(Long id, String email, String password) throws Exception {
        Credential credential = credentialGateway.findById(id);

        credential.setEmail(new Email(notNullOrDefault(email, credential.getEmail().getValue())));
        credential.setPassword(new Password(notNullOrDefault(passwordEncoder.encode(password), credential.getPassword().getValue())));

        credentialGateway.save(credential);
    }
}
