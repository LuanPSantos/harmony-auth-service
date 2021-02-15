package com.harmony.authservice.domain.auth.authentication.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Role;
import com.harmony.authservice.domain.credential.service.CredentialService;
import org.springframework.stereotype.Service;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.REFRESH_TOKEN_EXPIRATION_TIME;

@Service
public class AuthenticationService {

    private final CredentialService credentialService;

    public AuthenticationService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public JWTAuthorizationTokenPair authenticate(String username, String rawPassword) throws Exception {
        try {
            Credential credential = credentialService.findByEmail(username);

            if(credential.getPassword().matches(rawPassword)) {
                JWTAuthorization authorization = JWTAuthorization.withEmailAndRole(credential.getEmail().getValue(), credential.getRole());
                JWTAuthorization refreshAuthorization = JWTAuthorization
                        .withEmailAndExpirationTimeAndRole(credential.getEmail().getValue(), REFRESH_TOKEN_EXPIRATION_TIME, credential.getRole());

                return new JWTAuthorizationTokenPair(authorization, refreshAuthorization);
            }

            throw new AuthenticationException();
        }catch (CredentialNotFoundException exception) {
            throw new AuthenticationException();
        }
    }
}
