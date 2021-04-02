package com.harmony.authservice.domain.credential.usecase.read;

import com.harmony.authservice.domain.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.read.FindCredentialByEmailService;
import com.harmony.authservice.domain.credential.usecase.read.io.FindCredentialByEmailInput;
import com.harmony.authservice.domain.credential.usecase.read.io.FindCredentialByEmailOutput;
import org.springframework.stereotype.Service;

@Service
public class FindCredentialByEmailUseCase implements UseCase<FindCredentialByEmailInput, FindCredentialByEmailOutput> {

    private final FindCredentialByEmailService findCredentialByEmailService;

    public FindCredentialByEmailUseCase(FindCredentialByEmailService findCredentialByEmailService) {
        this.findCredentialByEmailService = findCredentialByEmailService;
    }

    @Override
    public FindCredentialByEmailOutput execute(FindCredentialByEmailInput payload) throws Exception {

        Credential credential = findCredentialByEmailService.findByEmail(payload.getEmail());


        return new FindCredentialByEmailOutput(
                credential.getId(),
                credential.getEmail(),
                credential.getPassword(),
                credential.getRole());
    }
}
