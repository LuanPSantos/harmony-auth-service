package com.harmony.authservice.domain.credential.exception;

public class PasswordInvalidException extends Exception {
    public static final String MESSAGE = "Senha inválida";
    public PasswordInvalidException() {
        super(MESSAGE);
    }
}
