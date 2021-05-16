package com.harmony.authservice.app.usecase.credential.read;

import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.read.FindCredentialByEmailService;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailInput;
import com.harmony.authservice.app.usecase.credential.read.io.FindCredentialByEmailOutput;
import org.springframework.stereotype.Service;

@Service
public class FindCredentialByEmailUseCase implements UseCase<FindCredentialByEmailInput, FindCredentialByEmailOutput> {

    private final FindCredentialByEmailService findCredentialByEmailService;

    public FindCredentialByEmailUseCase(FindCredentialByEmailService findCredentialByEmailService) {
        this.findCredentialByEmailService = findCredentialByEmailService;
    }

    @Override
    public FindCredentialByEmailOutput execute(FindCredentialByEmailInput input) throws Exception {

        Credential credential = findCredentialByEmailService.findByEmail(input.getEmail());


        return new FindCredentialByEmailOutput(
                credential.getId(),
                credential.getEmail(),
                credential.getPassword(),
                credential.getRole());
    }
}
