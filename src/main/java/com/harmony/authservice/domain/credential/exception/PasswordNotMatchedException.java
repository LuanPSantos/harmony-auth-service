package com.harmony.authservice.domain.credential.exception;

public class PasswordNotMatchedException extends Exception {

    public static final String MESSAGE = "Senha incorreta";

    public PasswordNotMatchedException() {
        super(MESSAGE);
    }
}
