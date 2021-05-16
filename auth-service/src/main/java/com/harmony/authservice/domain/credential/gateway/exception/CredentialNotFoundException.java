package com.harmony.authservice.domain.credential.gateway.exception;

public class CredentialNotFoundException extends Exception{
    public CredentialNotFoundException() {
        super("Credencial não entrada");
    }
}
