package com.harmony.authservice.app.usecase.passwordrecovery.createpassword.io;

import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.token.model.Token;

public class CreatePasswordInput {
    private final Token passwordRecoveryToken;
    private final Password password;

    public CreatePasswordInput(Token passwordRecoveryToken, Password password) {
        this.passwordRecoveryToken = passwordRecoveryToken;
        this.password = password;
    }

    public Token getPasswordRecoveryToken() {
        return passwordRecoveryToken;
    }

    public Password getPassword() {
        return password;
    }
}
