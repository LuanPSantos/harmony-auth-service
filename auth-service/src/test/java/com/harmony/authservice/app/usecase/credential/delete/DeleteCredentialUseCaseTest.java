package com.harmony.authservice.app.usecase.credential.delete;

import com.harmony.authservice.app.usecase.credential.delete.io.DeleteCredentialInput;
import com.harmony.authservice.app.utils.CredentialTestConstants;
import com.harmony.authservice.domain.credential.gateway.DeleteCredentialGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.harmony.authservice.app.utils.CredentialTestConstants.CREDENTIAL_ID;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class DeleteCredentialUseCaseTest {

    @Mock
    private DeleteCredentialGateway deleteCredentialGateway;

    @InjectMocks
    private DeleteCredentialUseCase deleteCredentialUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldDeleteACredential() {
        doNothing()
                .when(deleteCredentialGateway)
                .deleteById(eq(CREDENTIAL_ID));

        deleteCredentialUseCase.execute(new DeleteCredentialInput(CREDENTIAL_ID));

        verify(deleteCredentialGateway).deleteById(eq(CREDENTIAL_ID));
    }
}
