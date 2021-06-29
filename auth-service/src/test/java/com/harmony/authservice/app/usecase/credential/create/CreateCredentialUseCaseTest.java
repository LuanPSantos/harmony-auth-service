package com.harmony.authservice.app.usecase.credential.create;

import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialInput;
import com.harmony.authservice.app.usecase.credential.create.io.CreateCredentialOutput;
import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.gateway.CreateCredentialGateway;
import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.model.Role;
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
                .thenReturn(new Credential(CREDENTIAL_ID,CREDENTIAL_EMAIL, CREDENTIAL_PASSWORD, Role.USER));

        CreateCredentialOutput output = createCredentialUseCase
                .execute(new CreateCredentialInput(new Credential(CREDENTIAL_EMAIL, new Password(RAW_PASSWORD), Role.USER)));

        Credential credentialCaptured = captor.getValue();

        assertNull(credentialCaptured.getId());
        assertEquals(CREDENTIAL_EMAIL, credentialCaptured.getEmail());
        assertTrue(credentialCaptured.getPassword().matches(RAW_PASSWORD));
        assertEquals(Role.USER, credentialCaptured.getRole());

        assertEquals(CREDENTIAL_ID, output.getCredentialId());
    }

    @Test
    void ShouldNotCreateACredentialDuePasswordContainingEmail() {

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> {
            createCredentialUseCase.execute(new CreateCredentialInput(
                    new Credential(new Email("abc@email.com"), new Password("abc"), Role.USER)));
        });

        assertEquals("A senha não pode ser parte do email", exception.getMessage());
    }
}
