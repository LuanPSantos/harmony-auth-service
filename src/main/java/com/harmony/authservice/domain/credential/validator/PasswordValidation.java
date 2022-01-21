package com.harmony.authservice.domain.credential.validator;

import com.harmony.authservice.domain.credential.model.RawPassword;

public interface PasswordValidation {
    boolean isValid(RawPassword rawPassword);
}
