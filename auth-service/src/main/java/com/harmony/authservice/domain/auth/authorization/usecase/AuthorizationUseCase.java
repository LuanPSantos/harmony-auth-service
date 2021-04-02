package com.harmony.authservice.domain.auth.authorization.usecase;

import com.harmony.authservice.domain.auth.authorization.service.AuthorizationService;
import com.harmony.authservice.domain.auth.authorization.usecase.io.AuthorizationInput;
import com.harmony.authservice.domain.auth.authorization.usecase.io.AuthorizationOutput;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.usecase.UseCase;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationUseCase implements UseCase<AuthorizationInput, AuthorizationOutput> {

    private final AuthorizationService authorizationService;

    public AuthorizationUseCase(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public AuthorizationOutput execute(AuthorizationInput input) throws Exception {

        JWTAuthorization authorization = authorizationService.authorize(
                input.getAuthorizationToken(),
                input.getRefreshAuthorizationToken(),
                input.getRequiredRole()
        );

        return new AuthorizationOutput(
                authorization.getAuthorizationToken()
        );
    }
}
