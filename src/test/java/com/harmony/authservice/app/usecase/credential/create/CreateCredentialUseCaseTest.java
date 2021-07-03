package com.harmony.authservice.app.usecase.credential.create;

import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.gateway.CreateCredentialGateway;
import com.harmony.authservice.domain.credential.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.harmony.authservice.app.utils.CredentialTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CreateCredentialUseCaseTest {

    @Mock
    private CreateCredentialGateway createCredentialGateway;

    @InjectMocks
    private CreateCredentialUseCase createCredentialUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldCreateACredential() throws Exception {

        ArgumentCaptor<Credential> captor = ArgumentCaptor.forClass(Credential.class);
        when(createCredentialGateway.create(captor.capture()))
                .thenReturn(new Credential.Builder()
                        .withId(CREDENTIAL_ID)
                        .withEmail(EMAIL)
                        .withEncodedPassword(ENCODED_PASSWORD)
                        .withRole(Role.USER).build());

        CreateCredentialOutput output = createCredentialUseCase
                .execute(new CreateCredentialInput(new Credential.Builder()
                        .withEmail(EMAIL)
                        .withRawPassword(RAW_PASSWORD)
                        .withRole(Role.USER).build()));

        Credential credentialCaptured = captor.getValue();

        assertNull(credentialCaptured.getId());
        assertEquals(EMAIL, credentialCaptured.getEmail());
        assertTrue(credentialCaptured.getPassword().matches(RAW_PASSWORD));
        assertEquals(Role.USER, credentialCaptured.getRole());

        assertEquals(CREDENTIAL_ID, output.getCredentialId());
    }

    @Test
    void ShouldNotCreateACredentialDuePasswordContainingEmail() {

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> {
            createCredentialUseCase.execute(new CreateCredentialInput(
                    new Credential.Builder()
                            .withEmail("abc@email.com")
                            .withRawPassword("abc")
                            .withRole(Role.USER).build()));
        });

        assertEquals(PasswordInvalidException.MESSAGE, exception.getMessage());
    }
}
