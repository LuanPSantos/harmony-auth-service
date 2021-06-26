package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthenticationService {

    @Value("${auth.authorization-token.ttl}")
    private Long authorizationTokenTimeToLive;
    @Value("${auth.refresh-authorization-token.ttl}")
    private Long refreshAuthorizationTokenTimeToLive;

    private final CredentialGateway credentialGateway;

    public AuthenticationService(CredentialGateway credentialGateway) {
        this.credentialGateway = credentialGateway;
    }

    public JWTAuthorizationTokenPair authenticate(Email email, String rawPassword) throws Exception {
        try {
            Credential credential = credentialGateway.findByEmail(email);

            if (credential.getPassword().matches(rawPassword)) {
                JWTAuthorization authorization = createAuthorizationFor(credential);
                JWTAuthorization refreshAuthorization = createRefreshAuthorizationFor(credential);

                return new JWTAuthorizationTokenPair(
                        authorization,
                        refreshAuthorization
                );
            }

            throw new AuthenticationException();
        } catch (EntityNotFoundException exception) {
            throw new AuthenticationException();
        }
    }

    private JWTAuthorization createAuthorizationFor(Credential credential) {
        return JWTAuthorization.withEmailAndExpirationTimeAndRole(
                credential.getEmail().get(),
                authorizationTokenTimeToLive,
                credential.getRole()
        );
    }

    private JWTAuthorization createRefreshAuthorizationFor(Credential credential) {
        return JWTAuthorization.withEmailAndExpirationTimeAndRole(
                credential.getEmail().get(),
                refreshAuthorizationTokenTimeToLive,
                credential.getRole()
        );
    }
}
