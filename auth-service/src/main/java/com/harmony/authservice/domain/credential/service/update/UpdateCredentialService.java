package com.harmony.userregistration.credential.service.update;

import com.harmony.userregistration.credential.model.Credential;
import com.harmony.userregistration.credential.model.Email;
import com.harmony.userregistration.credential.model.Password;
import com.harmony.userregistration.credential.service.CredentialService;
import org.springframework.stereotype.Service;

import static com.harmony.userregistration.common.util.ObjectUtil.notNullOrDefault;

@Service
public class UpdateCredentialService {

    private final CredentialService credentialService;

    public UpdateCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public void update(Long id, String email, String password) throws Exception {
        Credential credential = credentialService.findById(id);

        credential.setEmail(new Email(notNullOrDefault(email, credential.getEmail().getValue())));
        credential.setPassword(new Password(notNullOrDefault(password, credential.getPassword().getValue())));

        credentialService.save(credential);
    }
}
