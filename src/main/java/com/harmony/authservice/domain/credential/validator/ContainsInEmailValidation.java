package com.harmony.authservice.domain.credential.validator;

import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.RawPassword;

public class ContainsInEmailValidation implements PasswordValidation {
    private final Email email;

    public ContainsInEmailValidation(Email email) {
        this.email = email;
    }

    @Override
    public boolean isValid(RawPassword rawPassword) {
        return !email.get().contains(rawPassword.get());
    }
}
