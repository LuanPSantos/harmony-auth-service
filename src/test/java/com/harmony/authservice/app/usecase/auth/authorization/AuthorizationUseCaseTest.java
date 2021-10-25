package com.harmony.authservice.app.usecase.auth.authorization;

import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationInput;
import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationOutput;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.service.AuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.harmony.authservice.app.utils.CredentialTestConstants.EMAIL;
import static com.harmony.authservice.app.utils.AuthorizationTestConstants.TTL;
import static com.harmony.authservice.domain.credential.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthorizationUseCaseTest {

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private AuthorizationUseCase authorizationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldAuthorizeUsingAuthorizationToken() throws Exception {
        JWTAuthorization authorization = new JWTAuthorization(
                EMAIL.get(),
                TTL,
                USER
        );
        JWTAuthorization refreshAuthorization = new JWTAuthorization(
                EMAIL.get(),
                TTL * 2,
                USER
        );

        when(authorizationService.authorize(
                eq(authorization.asToken()),
                eq(refreshAuthorization.asToken()),
                eq(USER))
        ).thenReturn(authorization);

        AuthorizationOutput output = authorizationUseCase
                .execute(new AuthorizationInput(authorization.asToken(), refreshAuthorization.asToken(), USER));

        assertEquals(authorization, output.getJwtAuthorization());
    }
}
