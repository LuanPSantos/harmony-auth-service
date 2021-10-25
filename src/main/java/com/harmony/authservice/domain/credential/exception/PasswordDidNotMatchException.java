package com.harmony.authservice.domain.credential.exception;

public class PasswordDidNotMatchException extends Exception {

    public static final String MESSAGE = "Senha incorreta";

    public PasswordDidNotMatchException() {
        super(MESSAGE);
    }
}
