package com.harmony.authservice.domain.auth.authentication.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthenticatedTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.REFRESH_TOKEN_EXPIRATION_TIME;

@Service
public class AuthenticationService {

    private final CredentialService credentialService;

    public AuthenticationService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public JWTAuthenticatedTokenPair authenticate(String username, String rawPassword) throws Exception {
        try {
            Credential credential = credentialService.findByEmail(username);

            if(credential.getPassword().matches(rawPassword)) {
                JWTAuthorization authorization = JWTAuthorization.withSubject(credential.getEmail().getValue());
                JWTAuthorization refreshAuthorization = JWTAuthorization
                        .withSubjectAndExpirationTime(credential.getEmail().getValue(), REFRESH_TOKEN_EXPIRATION_TIME);

                return new JWTAuthenticatedTokenPair(authorization, refreshAuthorization);
            }

            throw new AuthenticationException();
        }catch (CredentialNotFoundException exception) {
            throw new AuthenticationException();
        }
    }
}
