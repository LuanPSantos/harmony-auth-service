package com.harmony.authservice.domain.credential.validator;

import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;
import com.harmony.authservice.domain.credential.model.RawPassword;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    private final RawPassword rawPassword;

    private final List<PasswordValidation> validations = new ArrayList<>();

    public PasswordValidator(RawPassword rawPassword) {
        this.rawPassword = rawPassword;
    }

    public void validate() throws PasswordInvalidException {
        boolean isValid = validations
                .stream()
                .allMatch((validation -> validation.isValid(rawPassword)));

        if (!isValid) {
            throw new PasswordInvalidException();
        }
    }

    public void addValidation(PasswordValidation validation) {
        validations.add(validation);
    }
}
