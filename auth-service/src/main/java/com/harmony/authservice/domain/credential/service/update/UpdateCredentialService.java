package com.harmony.authservice.domain.credential.service.update;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.common.util.ObjectUtil.notNullOrDefault;

@Service
public class UpdateCredentialService {

    private final CredentialService credentialService;

    public UpdateCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public void update(Long id, String email, String password) throws Exception {
        Credential credential = credentialService.findById(id);

        credential.setEmail(new Email(notNullOrDefault(email, credential.getUsername())));
        credential.setPassword(new Password(notNullOrDefault(password, credential.getUsername())));

        credentialService.save(credential);
    }
}
