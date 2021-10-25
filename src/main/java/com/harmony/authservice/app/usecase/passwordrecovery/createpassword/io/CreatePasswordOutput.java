package com.harmony.authservice.app.usecase.passwordrecovery.createpassword.io;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;

public class CreatePasswordOutput {

    private final JWTAuthorization passwordRecoveryAuthorization;

    public CreatePasswordOutput(JWTAuthorization passwordRecoveryAuthorization) {
        this.passwordRecoveryAuthorization = passwordRecoveryAuthorization;
    }

    public JWTAuthorization getPasswordRecoveryAuthorization() {
        return passwordRecoveryAuthorization;
    }
}
