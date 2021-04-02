package com.harmony.authservice.domain.auth.authentication.usecase;

import com.harmony.authservice.domain.auth.authentication.service.AuthenticationService;
import com.harmony.authservice.domain.auth.authentication.usecase.io.AuthenticationInput;
import com.harmony.authservice.domain.auth.authentication.usecase.io.AuthenticationOutput;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.usecase.UseCase;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCase implements UseCase<AuthenticationInput, AuthenticationOutput> {

    private final AuthenticationService authenticationService;

    public AuthenticationUseCase(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public AuthenticationOutput execute(AuthenticationInput input) throws Exception {

        JWTAuthorizationTokenPair authorizationTokenPair = authenticationService.authenticate(
                input.getEmail(),
                input.getRawPassword()
        );

        return new AuthenticationOutput(
                authorizationTokenPair.getAuthorization().getAuthorizationToken(),
                authorizationTokenPair.getRefreshAuthorization().getAuthorization()
        );
    }
}
