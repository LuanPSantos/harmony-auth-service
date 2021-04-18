package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.gateway.exception.CredentialNotFoundException;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.gateway.CredentialGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public JWTAuthorizationTokenPair authenticate(String email, String rawPassword) throws Exception {
        try {
            Credential credential = credentialGateway.findByEmail(email);

            if(credential.getPassword().matches(rawPassword)) {
                JWTAuthorization authorization = createAuthorization(credential);
                JWTAuthorization refreshAuthorization = createRefreshAuthorization(credential);

                return new JWTAuthorizationTokenPair(
                        authorization,
                        refreshAuthorization
                );
            }

            throw new AuthenticationException();
        }catch (CredentialNotFoundException exception) {
            throw new AuthenticationException();
        }
    }

    private JWTAuthorization createAuthorization(Credential credential) {
        return JWTAuthorization.withEmailAndExpirationTimeAndRole(
                credential.getEmail().getValue(),
                authorizationTokenTimeToLive,
                credential.getRole()
        );
    }

    private JWTAuthorization createRefreshAuthorization(Credential credential) {
        return JWTAuthorization.withEmailAndExpirationTimeAndRole(
                credential.getEmail().getValue(),
                refreshAuthorizationTokenTimeToLive,
                credential.getRole()
        );
    }
}