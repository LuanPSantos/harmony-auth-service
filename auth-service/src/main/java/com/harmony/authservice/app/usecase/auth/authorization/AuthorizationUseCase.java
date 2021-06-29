package com.harmony.authservice.app.usecase.auth.authorization;

import com.harmony.authservice.domain.auth.service.AuthorizationService;
import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationInput;
import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationOutput;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.app.usecase.UseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationUseCase implements UseCase<AuthorizationInput, AuthorizationOutput> {

    private final AuthorizationService authorizationService;

    public AuthorizationUseCase(
                    @Value("${auth.authorization-token.ttl}")
                    Long authorizationTokenTimeToLive) {
        this.authorizationService = new AuthorizationService(authorizationTokenTimeToLive);
    }

    @Override
    public AuthorizationOutput execute(AuthorizationInput input) throws Exception {

        JWTAuthorization authorization = authorizationService.authorize(
                input.getAuthorizationToken(),
                input.getRefreshAuthorizationToken(),
                input.getRequiredRole()
        );

        return new AuthorizationOutput(authorization);
    }
}
