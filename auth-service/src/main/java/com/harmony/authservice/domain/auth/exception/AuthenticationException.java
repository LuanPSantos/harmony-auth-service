package com.harmony.authservice.domain.auth.exception;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Email ou senha incorretos.");
    }
}
