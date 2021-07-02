package com.harmony.authservice.domain.credential.model;

public abstract class Password {
    protected final String value;

    public Password(String value) {
        this.value = value;
    }

    public abstract String get();

    public abstract boolean matches(Password password);
}
