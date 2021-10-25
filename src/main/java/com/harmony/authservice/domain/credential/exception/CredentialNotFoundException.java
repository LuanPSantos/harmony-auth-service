package com.harmony.authservice.domain.credential.exception;

public class CredentialNotFoundException extends Exception {
    public CredentialNotFoundException() {
        super("Credencial não entrada");
    }
}
