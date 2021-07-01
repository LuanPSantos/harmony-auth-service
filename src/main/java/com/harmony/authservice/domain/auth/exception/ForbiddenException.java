package com.harmony.authservice.domain.auth.exception;

public class ForbiddenException extends Exception {

    public ForbiddenException() {
        super("O usuário não tem permissão pra isso.");
    }
}
