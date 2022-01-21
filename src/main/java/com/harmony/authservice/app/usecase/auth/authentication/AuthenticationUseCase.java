package com.harmony.authservice.app.usecase.auth.authentication;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationInput;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationOutput;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.app.usecase.UseCase;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCase implements UseCase<AuthenticationInput, AuthenticationOutput> {

    private final AuthenticationService authenticationService;
    private final CredentialQueryGateway credentialQueryGateway;

    public AuthenticationUseCase(AuthenticationService authenticationService, CredentialQueryGateway credentialQueryGateway) {
        this.authenticationService = authenticationService;
        this.credentialQueryGateway = credentialQueryGateway;
    }

    @Override
    public AuthenticationOutput execute(AuthenticationInput input) throws AuthenticationException {

        Credential credential = findCredentialByEmail(input.getEmail());

        JWTAuthorizationTokenPair authorizationTokenPair = authenticationService.authenticate(
                credential,
                input.getRawPassword()
        );

        return new AuthenticationOutput(authorizationTokenPair, credential.getId());
    }

    private Credential findCredentialByEmail(Email email) throws AuthenticationException {
        return credentialQueryGateway
                .findByEmail(email)
                .orElseThrow(AuthenticationException::new);
    }
}
