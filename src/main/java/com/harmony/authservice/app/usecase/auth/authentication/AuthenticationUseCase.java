package com.harmony.authservice.app.usecase.auth.authentication;

import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationInput;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationOutput;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.app.usecase.UseCase;
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

        return new AuthenticationOutput(authorizationTokenPair);
    }
}
