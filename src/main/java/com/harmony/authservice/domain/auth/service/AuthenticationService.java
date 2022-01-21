package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.RawPassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final Long authorizationTokenTimeToLive;
    private final Long refreshAuthorizationTokenTimeToLive;

    public AuthenticationService(
            @Value("${auth.authorization-token.ttl}")
                    Long authorizationTokenTTL,
            @Value("${auth.refresh-authorization-token.ttl}")
                    Long refreshAuthorizationTokenTTL
    ) {
        this.authorizationTokenTimeToLive = authorizationTokenTTL;
        this.refreshAuthorizationTokenTimeToLive = refreshAuthorizationTokenTTL;
    }

    public JWTAuthorizationTokenPair authenticate(Credential credential, RawPassword rawPassword) throws AuthenticationException {
        if (credential.getPassword().matches(rawPassword)) {
            JWTAuthorization authorization = createAuthorizationFor(credential);
            JWTAuthorization refreshAuthorization = createRefreshAuthorizationFor(credential);

            return new JWTAuthorizationTokenPair(
                    authorization,
                    refreshAuthorization
            );
        }

        throw new AuthenticationException();
    }

    private JWTAuthorization createAuthorizationFor(Credential credential) {
        return new JWTAuthorization(
                credential.getEmail().get(),
                authorizationTokenTimeToLive,
                credential.getRole()
        );
    }

    private JWTAuthorization createRefreshAuthorizationFor(Credential credential) {
        return new JWTAuthorization(
                credential.getEmail().get(),
                refreshAuthorizationTokenTimeToLive,
                credential.getRole()
        );
    }
}
