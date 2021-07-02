package com.harmony.authservice.domain.credential.model;

import java.util.Objects;

public class RawPassword extends Password {

    public RawPassword(String value) {
        super(value);
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public boolean matches(Password password) {
        return Objects.equals(value, password.get());
    }
}
