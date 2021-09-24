package com.harmony.authservice.app.usecase.passwordrecovery.createpassword.io;

import com.harmony.authservice.domain.credential.model.Password;

public class CreatePasswordInput {
    private final Password rawPassword;

    public CreatePasswordInput(Password rawPassword) {
        this.rawPassword = rawPassword;
    }

    public Password getRawPassword() {
        return rawPassword;
    }
}
