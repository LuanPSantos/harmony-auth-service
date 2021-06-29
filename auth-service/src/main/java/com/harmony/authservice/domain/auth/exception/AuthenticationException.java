package com.harmony.authservice.domain.auth.exception;

public class AuthenticationException extends Exception {

    public static final String MESSAGE = "Email ou senha incorretos.";

    public AuthenticationException() {
        super(MESSAGE);
    }
}
