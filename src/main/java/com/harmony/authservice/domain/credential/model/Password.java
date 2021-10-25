package com.harmony.authservice.domain.credential.model;

import java.util.Objects;

public class Password {
    protected final String value;

    public Password(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public boolean matches(Password password) {
        return Objects.equals(value, password.get());
    }
}
