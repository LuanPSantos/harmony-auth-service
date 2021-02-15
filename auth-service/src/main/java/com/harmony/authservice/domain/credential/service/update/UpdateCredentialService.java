package com.harmony.authservice.domain.credential.service.update;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.common.util.ObjectUtils.notNullOrDefault;

@Service
public class UpdateCredentialService {

    private final CredentialService credentialService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UpdateCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public void update(Long id, String email, String password) throws Exception {
        Credential credential = credentialService.findById(id);

        credential.setEmail(new Email(notNullOrDefault(email, credential.getEmail().getValue())));
        credential.setPassword(new Password(notNullOrDefault(passwordEncoder.encode(password), credential.getPassword().getValue())));

        credentialService.save(credential);
    }
}
