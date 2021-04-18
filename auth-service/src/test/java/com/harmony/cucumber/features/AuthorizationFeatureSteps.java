package com.harmony.cucumber.features;

import com.harmony.authservice.app.usecase.auth.authorization.AuthorizationUseCase;
import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationInput;
import com.harmony.authservice.app.usecase.auth.authorization.io.AuthorizationOutput;
import com.harmony.authservice.domain.auth.exception.ForbiddenException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.service.AuthorizationService;
import com.harmony.authservice.domain.credential.model.Role;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.harmony.authservice.domain.auth.model.JWTAuthorization.withEmailAndExpirationTimeAndRole;
import static com.harmony.authservice.domain.credential.model.Role.valueOf;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthorizationFeatureSteps {

    private JWTAuthorization authorization;
    private Role requiredRole;

    private AuthorizationInput input;

    @Mock
    private AuthorizationService authorizationService;
    @InjectMocks
    private AuthorizationUseCase authorizationUseCase;

    public AuthorizationFeatureSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("an authenticated system's user with email {string} and role {string}")
    public void an_authenticated_system_s_user_with_email_and_role(String email, String role) {
        Long expirationTime = 1000L;
        authorization = withEmailAndExpirationTimeAndRole(email, expirationTime, valueOf(role));
    }

    @Given("a required role {string}")
    public void a_required_role(String role) {
        requiredRole = Role.valueOf(role);
    }

    @When("the system's user is authorized by his role")
    public void the_system_s_user_is_authorized_by_his_role() throws ForbiddenException {
        when(
                authorizationService.authorize(
                        eq(authorization.getAuthorizationToken()),
                        eq(authorization.getAuthorizationToken()),
                        eq(requiredRole)
                )
        ).thenReturn(authorization);
    }
    @Then("should return the authorization data")
    public void should_return_the_authorization_data() throws Exception {
        AuthorizationOutput output = authorizationUseCase.execute(
                new AuthorizationInput(
                        authorization.getAuthorizationToken(),
                        authorization.getAuthorizationToken(),
                        requiredRole
                )
        );

        Assert.assertEquals(authorization.getAuthorizationToken(), output.getAuthorizationToken());
    }

    @When("the system's user is unauthorized by his role")
    public void the_system_s_user_is_unauthorized_by_his_role() throws ForbiddenException {
        when(
                authorizationService.authorize(
                        eq(authorization.getAuthorizationToken()),
                        eq(authorization.getAuthorizationToken()),
                        eq(requiredRole)
                )
        ).thenThrow(ForbiddenException.class);
    }

    @Then("should be forbidden to access the resource")
    public void should_be_forbidden_to_access_the_resource() {
        Assert.assertThrows(ForbiddenException.class, () -> {
            authorizationUseCase.execute(
                    new AuthorizationInput(
                            authorization.getAuthorizationToken(),
                            authorization.getAuthorizationToken(),
                            requiredRole
                    )
            );
        });
    }
}
