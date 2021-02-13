package com.harmony.authservice.domain.auth.authorization.exception;

public class AuthorizationExpiredException extends Exception{
    public AuthorizationExpiredException() {
        super("Autorização expirada.");
    }
}
