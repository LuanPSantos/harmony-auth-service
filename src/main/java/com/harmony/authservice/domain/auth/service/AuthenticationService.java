package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.gateway.CredentialQueryGateway;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Password;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final Long authorizationTokenTimeToLive;
    private final Long refreshAuthorizationTokenTimeToLive;

    private final CredentialQueryGateway credentialQueryGateway;

    public AuthenticationService(
            CredentialQueryGateway credentialQueryGateway,
            @Value("${auth.authorization-token.ttl}")
            Long authorizationTokenTTL,
            @Value("${auth.refresh-authorization-token.ttl}")
            Long refreshAuthorizationTokenTTL
    ) {
        this.credentialQueryGateway = credentialQueryGateway;
        this.authorizationTokenTimeToLive = authorizationTokenTTL;
        this.refreshAuthorizationTokenTimeToLive = refreshAuthorizationTokenTTL;
    }

    public JWTAuthorizationTokenPair authenticate(Email email, Password rawPassword) throws Exception {
        try {
            Credential credential = credentialQueryGateway.findByEmail(email);

            if (credential.getPassword().matches(rawPassword)) {
                JWTAuthorization authorization = createAuthorizationFor(credential);
                JWTAuthorization refreshAuthorization = createRefreshAuthorizationFor(credential);

                return new JWTAuthorizationTokenPair(
                        authorization,
                        refreshAuthorization
                );
            }

            throw new AuthenticationException();
        } catch (CredentialNotFoundException exception) {
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
