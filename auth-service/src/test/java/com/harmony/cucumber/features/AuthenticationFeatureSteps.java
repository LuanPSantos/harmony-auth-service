package com.harmony.cucumber.features;

import com.harmony.authservice.app.usecase.auth.authentication.AuthenticationUseCase;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationInput;
import com.harmony.authservice.app.usecase.auth.authentication.io.AuthenticationOutput;
import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.model.JWTAuthorizationTokenPair;
import com.harmony.authservice.domain.auth.service.AuthenticationService;
import com.harmony.authservice.domain.credential.model.Role;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AuthenticationFeatureSteps {

    private AuthenticationInput authenticationInput;
    private AuthenticationOutput authenticationOutput;
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    public AuthenticationFeatureSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("a credential composed by {string} and {string}")
    public void a_credential_composed_by_and(String email, String rawPassword) {
        authenticationInput = new AuthenticationInput(email, rawPassword);
    }

    @When("authentication succeed")
    public void authentication_succeed() throws Exception {
        when(
                authenticationService.authenticate(
                        eq(authenticationInput.getEmail()),
                        eq(authenticationInput.getRawPassword()))
        ).thenReturn(new JWTAuthorizationTokenPair(
                JWTAuthorization.withEmailAndExpirationTimeAndRole(authenticationInput.getEmail(), 60_000L, Role.USER),
                JWTAuthorization.withEmailAndExpirationTimeAndRole(authenticationInput.getEmail(), 60_000L, Role.USER)
        ));

        authenticationOutput = authenticationUseCase.execute(authenticationInput);
    }

    @When("authentication fail by invalid credential")
    public void authentication_fail_by_invalid_credential() throws Exception {
        when(
                authenticationService.authenticate(
                        eq(authenticationInput.getEmail()),
                        eq(authenticationInput.getRawPassword()))
        ).thenThrow(AuthenticationException.class);
    }

    @Then("should receive a error message with text {string}")
    public void should_receive_a_error_message_with_text(String string) {
        Assert.assertThrows(AuthenticationException.class, () -> {
            authenticationUseCase.execute(authenticationInput);
        });
    }

    @Then("should receive the authorization tokens")
    public void should_receive_the_authorization_tokens() {
        Assert.assertNotNull(authenticationOutput.getAuthorizationToken());
        Assert.assertNotNull(authenticationOutput.getRefreshAuthorizationToken());
    }
}
