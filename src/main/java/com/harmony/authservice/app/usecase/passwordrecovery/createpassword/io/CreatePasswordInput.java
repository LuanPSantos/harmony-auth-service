package com.harmony.authservice.app.usecase.passwordrecovery.createpassword.io;

import com.harmony.authservice.domain.credential.model.RawPassword;
import com.harmony.authservice.domain.token.model.Token;

public class CreatePasswordInput {
    private final Token passwordRecoveryToken;
    private final RawPassword password;

    public CreatePasswordInput(Token passwordRecoveryToken, RawPassword password) {
        this.passwordRecoveryToken = passwordRecoveryToken;
        this.password = password;
    }

    public Token getPasswordRecoveryToken() {
        return passwordRecoveryToken;
    }

    public RawPassword getPassword() {
        return password;
    }
}
